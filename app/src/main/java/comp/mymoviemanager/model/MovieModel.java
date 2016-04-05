package comp.mymoviemanager.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

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
import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class MovieModel extends Observable{

    LinkedList<Movie> suggestions = new LinkedList<>();
    LinkedList<Movie> popular = new LinkedList<>();
    LinkedList<Movie> top_rated = new LinkedList<>();
    LinkedList<Movie> upcoming = new LinkedList<>();
    LinkedList<Movie> search_results = new LinkedList<>();
    LinkedList<Bitmap> images = new LinkedList<>();

    //private final String api_key = "F088t4s6QGI5T92W3Nwiju8jFU52J8SP";
    //public final static String apiURL = "http://api.bigoven.com/recipes?";
    private final String api_key = "9e848c636182f849c60c808276757408";
    public final static String apiURL = "http://api.themoviedb.org/3/";
    private int func = -1;
    private int func1 = -1;

    public void makeSearch(String query){
        query = query.replace(" ", "+");
        String url = apiURL + "search/movie?query=" + query + "&api_key=" + api_key;
        func = 4;
        search_results.clear();
        new CallAPI().execute(url);
    }

    public void getSuggestions(){
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
                        suggestions.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),"",participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id"))));
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
                        popular.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),"",participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id"))));
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
                        top_rated.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),"",participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id"))));
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
                        upcoming.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),"",participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id"))));
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
                        search_results.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),"",participant.getString("vote_average"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path"),Integer.parseInt(participant.getString("id"))));
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

}
