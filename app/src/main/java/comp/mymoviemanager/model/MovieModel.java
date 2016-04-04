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
    LinkedList<Movie> new_in_theatres = new LinkedList<>();
    LinkedList<Movie> search_results = new LinkedList<>();
    LinkedList<Bitmap> images = new LinkedList<>();

    //private final String api_key = "F088t4s6QGI5T92W3Nwiju8jFU52J8SP";
    //public final static String apiURL = "http://api.bigoven.com/recipes?";
    private final String api_key = "9e848c636182f849c60c808276757408";
    public final static String apiURL = "http://api.themoviedb.org/3/";
    private int func = -1;
    private int cont = 0;

    public void getSuggestions(){
        //String url = apiURL + "title_kw=" + text + "&pg=1&rpp=20&api_key=" + api_key;
        String url = apiURL + "movie/popular?api_key=" + api_key;
        func = 0;
        new CallAPI().execute(url);
    }

    public void getImages(){
        for (int i = 0; i < suggestions.size(); i++){
            new GetPoster().execute("http://image.tmdb.org/t/p/w154" + suggestions.get(i).getPosterPath());
        }
    }

    public void setImages(){
        for (int i = 0; i < suggestions.size(); i++){
            suggestions.get(i).setPoster(images.get(i));
        }
        setChanged();
        notifyObservers(0);
    }

    public LinkedList<Movie> getSuggestionsResult(){
        return suggestions;
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
                        suggestions.add(new Movie(participant.getString("original_title"),participant.getString("release_date"),"",participant.getString("popularity"),participant.getString("original_language"),participant.getString("overview"),participant.getString("poster_path")));
                    }
                    getImages();
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
            if(images.size() == suggestions.size()){
                setImages();
            }
            //Movie.setPoster(result);
            //System.out.println(getPoster());
        }
    }

}
