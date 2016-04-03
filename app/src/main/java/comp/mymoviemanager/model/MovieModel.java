package comp.mymoviemanager.model;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class MovieModel {

    LinkedList<Movie> suggestions = new LinkedList<>();
    LinkedList<Movie> new_in_theatres = new LinkedList<>();
    LinkedList<Movie> search_results = new LinkedList<>();

    private final String api_key = "F088t4s6QGI5T92W3Nwiju8jFU52J8SP";
    private int func = -1;
    public final static String apiURL = "http://api.bigoven.com/recipes?";



/*    public LinkedList<Movie> getSuggestions(String text){
        String url = apiURL + "title_kw=" + text + "&pg=1&rpp=20";
        LinkedList<Movie> suggestions;
        new CallAPI().execute(url);
        return suggestions;
    }*/

    public void getSuggestions(String text){
        String url = apiURL + "title_kw=" + text + "&pg=1&rpp=20&api_key=" + api_key;
        func = 0;
        new CallAPI().execute(url);
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
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

           /*
            // HTTP Get
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
                System.out.println(in);
            } catch (Exception e ) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }

            */




            /*// Parse XML
            XmlPullParserFactory pullParserFactory;
            try {
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                result = parseXML(parser);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/


            //return resultToDisplay;

        }

        @Override
        protected void onPostExecute(String result){
            if(func == 0){
                try {
                    JSONObject suggestionsObj = new JSONObject(result);
                    System.out.println(suggestionsObj.getString("Results"));
                    func = -1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    func = -1;
                }
            }
        }
    }
}
