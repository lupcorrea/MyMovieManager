package comp.mymoviemanager.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observable;

import comp.mymoviemanager.util.DatabaseManager;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class ApplicationModel extends Observable{

    LinkedList<Movie> suggestions = new LinkedList<>();
    LinkedList<Movie> popular = new LinkedList<>();
    LinkedList<Movie> top_rated = new LinkedList<>();
    LinkedList<Movie> upcoming = new LinkedList<>();
    LinkedList<Movie> search_results = new LinkedList<>();
    LinkedList<Bitmap> images = new LinkedList<>();

    Profile profile = Profile.getInstance();

    //private final String api_key = "F088t4s6QGI5T92W3Nwiju8jFU52J8SP";
    //public final static String apiURL = "http://api.bigoven.com/recipes?";
    private final String api_key = "9e848c636182f849c60c808276757408";
    public final static String apiURL = "http://api.themoviedb.org/3/";
    private int func = -1;
    private int func1 = -1;

    public Movie selected = null;
    public Hashtable<String,String> genres = new Hashtable<>();

    /* Database methods */
    public LinkedList addToLiked (Movie m, DatabaseManager db) {
        profile.addMovieToTopList(m);
        profile.setTopList(db);
        return profile.getTopList(db);
    }

    public LinkedList addToHated (Movie m, DatabaseManager db) {
        profile.addMovieToBottomList(m);
        profile.setBottomList(db);
        return profile.getBottomList(db);
    }

    public LinkedList addToFuture (Movie m, DatabaseManager db) {
        profile.addMovieToFutureList(m);
        profile.setFutureList(db);
        return profile.getFutureList(db);
    }

    public Movie getSelected(){
        return selected;
    }

    public void setMovieFromId(Integer id){
        int found = 0;
        while (found == 0) {
            for (int i = 0; i < suggestions.size() && found == 0; i++) {
                try {
                    if (suggestions.get(i).getId().intValue() == id.intValue()) {
                        selected = suggestions.get(i);
                        found = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < popular.size() && found == 0; i++) {
                try {
                    if (popular.get(i).getId().intValue() == id.intValue()) {
                        selected = popular.get(i);
                        found = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < top_rated.size() && found == 0; i++) {
                try {
                    if (top_rated.get(i).getId().intValue() == id.intValue()) {
                        selected = top_rated.get(i);
                        found = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < upcoming.size() && found == 0; i++) {
                try {
                    if (upcoming.get(i).getId().intValue() == id.intValue()) {
                        selected = upcoming.get(i);
                        found = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < search_results.size() && found == 0; i++) {
                try {
                    if (search_results.get(i).getId().intValue() == id.intValue()) {
                        selected = search_results.get(i);
                        found = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void makeSearch(String query){
        query = query.replace(" ", "+");
        String url = apiURL + "search/movie?query=" + query + "&api_key=" + api_key;
        func = 4;
        search_results.clear();
        new CallAPI().execute(url);
    }

    public void clearLists(){
        suggestions.clear();
        upcoming.clear();
        popular.clear();
        top_rated.clear();
        System.gc();
    }

    public void getSuggestions(){
        clearLists();
        //String url = apiURL + "title_kw=" + text + "&pg=1&rpp=20&api_key=" + api_key;
        String url = apiURL + "movie/popular?api_key=" + api_key;
        func = 0;
        new CallAPI().execute(url);
    }

    public void getPopulars(){
        //String url = apiURL + "title_kw=" + text + "&pg=1&rpp=20&api_key=" + api_key;
        String url = apiURL + "movie/popular?api_key=" + api_key;
        func = 1;
        new CallAPI().execute(url);
    }

    public void getTopRated(){
        //String url = apiURL + "title_kw=" + text + "&pg=1&rpp=20&api_key=" + api_key;
        String url = apiURL + "movie/top_rated?api_key=" + api_key;
        func = 2;
        new CallAPI().execute(url);
    }

    public void getUpcoming(){
        //String url = apiURL + "title_kw=" + text + "&pg=1&rpp=20&api_key=" + api_key;
        String url = apiURL + "movie/upcoming?api_key=" + api_key;
        func = 3;
        new CallAPI().execute(url);
    }

    public void getImages(Integer x){
        if (x == 0) {
            func1 = 0;
            for (int i = 0; i < suggestions.size(); i++) {
                new GetPoster().execute("http://image.tmdb.org/t/p/w154" + suggestions.get(i).getPosterPath());
            }
        }
        else if (x == 1){
            func1 = 1;
            for (int i = 0; i < popular.size(); i++) {
                new GetPoster().execute("http://image.tmdb.org/t/p/w154" + popular.get(i).getPosterPath());
            }
        }
        else if (x == 2){
            func1 = 2;
            for (int i = 0; i < top_rated.size(); i++) {
                new GetPoster().execute("http://image.tmdb.org/t/p/w154" + top_rated.get(i).getPosterPath());
            }
        }
        else if (x == 3){
            func1 = 3;
            for (int i = 0; i < upcoming.size(); i++) {
                new GetPoster().execute("http://image.tmdb.org/t/p/w154" + upcoming.get(i).getPosterPath());
            }
        }
        else if (x == 4){
            func1 = 4;
            for (int i = 0; i < search_results.size(); i++) {
                new GetPoster().execute("http://image.tmdb.org/t/p/w154" + search_results.get(i).getPosterPath());
            }
        }
    }

    public void setImages(){
        if (func1 == 0){
            for (int i = 0; i < suggestions.size(); i++){
                suggestions.get(i).setPoster(images.get(i));
            }
            images.clear();
            setChanged();
            notifyObservers(0);
            getPopulars();
        }
        else if (func1 == 1) {
            for (int i = 0; i < popular.size(); i++) {
                popular.get(i).setPoster(images.get(i));
            }
            images.clear();
            setChanged();
            notifyObservers(1);
            getTopRated();
        }
        else if (func1 == 2) {
            for (int i = 0; i < top_rated.size(); i++) {
                top_rated.get(i).setPoster(images.get(i));
            }
            images.clear();
            setChanged();
            notifyObservers(2);
            getUpcoming();
        }
        else if (func1 == 3) {
            for (int i = 0; i < upcoming.size(); i++) {
                upcoming.get(i).setPoster(images.get(i));
            }
            images.clear();
            setChanged();
            notifyObservers(3);
        }
        else if (func1 == 4) {
            for (int i = 0; i < search_results.size(); i++) {
                search_results.get(i).setPoster(images.get(i));
            }
            images.clear();
            setChanged();
            notifyObservers(4);
        }
        func1 = -1;
    }

    public LinkedList<Movie> getResult(Integer x){
        LinkedList<Movie> result = new LinkedList<>();
        if (x == 0)
            result = suggestions;
        else if (x == 1)
            result = popular;
        else if (x == 2)
            result = top_rated;
        else if (x == 3)
            result = upcoming;
        return result;
    }

    public LinkedList<Movie> getSearch_results(){
        LinkedList<Movie> result = search_results;
        return result;
    }

    public class CallAPI extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            String urlString=params[0]; // URL to call
            //String resultToDisplay = "";
            //InputStream in = null;


            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept","application/json");
                urlConnection.setRequestProperty("Content-Type","application/json");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result){
            if(func == 0){
                try {
                    JSONObject suggestionsObj = new JSONObject(result);
                    JSONArray json_array_participants = suggestionsObj.getJSONArray("results");
                    for(int i=0; i < json_array_participants.length(); i++) {
                        JSONObject participant = json_array_participants.getJSONObject(i);
                        //System.out.println(participant.getString("original_title"));
                        suggestions.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id")),participant.getString("genre_ids")));
                    }
                    getImages(0);
                    func = -1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    func = -1;
                }
            }
            else if(func == 1){
                try {
                    JSONObject suggestionsObj = new JSONObject(result);
                    JSONArray json_array_participants = suggestionsObj.getJSONArray("results");
                    for(int i=0; i < json_array_participants.length(); i++) {
                        JSONObject participant = json_array_participants.getJSONObject(i);
                        //System.out.println(participant.getString("original_title"));
                        popular.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id")),participant.getString("genre_ids")));
                    }
                    getImages(1);
                    func = -1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    func = -1;
                }
            }
            else if(func == 2){
                try {
                    JSONObject suggestionsObj = new JSONObject(result);
                    JSONArray json_array_participants = suggestionsObj.getJSONArray("results");
                    for(int i=0; i < json_array_participants.length(); i++) {
                        JSONObject participant = json_array_participants.getJSONObject(i);
                        //System.out.println(participant.getString("original_title"));
                        top_rated.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id")),participant.getString("genre_ids")));
                    }
                    getImages(2);
                    func = -1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    func = -1;
                }
            }
            else if(func == 3){
                try {
                    JSONObject suggestionsObj = new JSONObject(result);
                    JSONArray json_array_participants = suggestionsObj.getJSONArray("results");
                    for(int i=0; i < json_array_participants.length(); i++) {
                        JSONObject participant = json_array_participants.getJSONObject(i);
                        //System.out.println(participant.getString("original_title"));
                        upcoming.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id")),participant.getString("genre_ids")));
                    }
                    getImages(3);
                    func = -1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    func = -1;
                }
            }
            else if(func == 4){
                try {
                    JSONObject suggestionsObj = new JSONObject(result);
                    JSONArray json_array_participants = suggestionsObj.getJSONArray("results");
                    System.out.println(json_array_participants);
                    for(int i=0; i < json_array_participants.length(); i++) {
                        JSONObject participant = json_array_participants.getJSONObject(i);
                        //System.out.println(participant.getString("original_title"));
                        search_results.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id")),participant.getString("genre_ids")));
                    }
                    getImages(4);
                    func = -1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    func = -1;
                }
            }
        }
    }

    //////////
    public class GetPoster extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try
            {
                URL url;
                url = new URL( params[0] );

                HttpURLConnection c = ( HttpURLConnection ) url.openConnection();
                c.setDoInput( true );
                c.connect();
                InputStream is = c.getInputStream();
                Bitmap img;
                img = BitmapFactory.decodeStream(is);
                return img;
            }
            catch ( MalformedURLException e )
            {
                Log.d("RemoteImageHandler", "fetchImage passed invalid URL: " + params[0]);
            }
            catch ( IOException e )
            {
                Log.d("RemoteImageHandler", "fetchImage IO exception: " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            images.add(result);
            if (func1 == 0) {
                if (images.size() == suggestions.size()) {
                    setImages();
                }
            }
            else if (func1 == 1) {
                if (images.size() == popular.size()) {
                    setImages();
                }
            }
            else if (func1 == 2) {
                if (images.size() == top_rated.size()) {
                    setImages();
                }
            }
            else if (func1 == 3) {
                if (images.size() == upcoming.size()) {
                    setImages();
                }
            }
            else if (func1 == 4) {
                if (images.size() == search_results.size()) {
                    setImages();
                }
            }
        }
    }

    public ApplicationModel(){
        genres.put("28","Action");
        genres.put("12", "Adventure");
        genres.put("16", "Animation");
        genres.put("35", "Comedy");
        genres.put("80", "Crime");
        genres.put("99", "Documentary");
        genres.put("18", "Drama");
        genres.put("10751", "Family");
        genres.put("14", "Fantasy");
        genres.put("10769", "Foreign");
        genres.put("36", "History");
        genres.put("27", "Horror");
        genres.put("10402", "Music");
        genres.put("9648", "Mystery");
        genres.put("10749", "Romance");
        genres.put("878", "Science Fiction");
        genres.put("10770", "TV Movie");
        genres.put("53", "Thriller");
        genres.put("10752", "War");
        genres.put("37", "Western");
    }
}
