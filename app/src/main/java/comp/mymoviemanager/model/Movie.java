package comp.mymoviemanager.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import comp.mymoviemanager.model.MovieModel;

/**
 * Author: lupcorrea
 * Day: 01/04/16
 */

/* TODO: Edit the release methods so it only deals with years. */
/* TODO: Add the lists for actors and producers, if possible. */
public class Movie {
    private String name;
    private String release;
    private String category;
    private String popularity;
    private String language;
    private String sinopsis;
    private String poster_path;
    private Bitmap poster;
    private Integer id;

    public Movie(String name, String release, String category, String popularity, String language, String sinopsis, String poster_path, Integer id){
        this.name = name;
        this.release = release;
        this.category = category;
        this.popularity = popularity;
        this.language = language;
        this.sinopsis = sinopsis;
        this.poster_path = poster_path;
        this.id = id;
        //new GetPoster().execute("http://image.tmdb.org/t/p/w500" + poster_path);
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPosterPath(String poster_path) { this.poster_path = poster_path; }

    public String getPosterPath() { return poster_path; }

    public void setPoster(Bitmap img) { this.poster = img; }

    public Bitmap getPoster() {
        return poster;
    }

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
                Log.d( "RemoteImageHandler", "fetchImage IO exception: " + e );
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            setPoster(result);
            System.out.println(getPoster());
        }
    }
}
