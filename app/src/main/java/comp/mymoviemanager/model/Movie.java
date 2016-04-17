package comp.mymoviemanager.model;

import android.graphics.Bitmap;

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
    private String genre_list;
    private int myVote;

    public Movie(String name, String release, String popularity, String language, String sinopsis, String poster_path, Integer id, String genre_list){
        this.name = name;
        this.release = release;
        this.popularity = popularity;
        this.language = language;
        this.sinopsis = sinopsis;
        this.poster_path = poster_path;
        this.id = id;
        this.genre_list = genre_list;
    }

    /*protected void finalize() throws Throwable{
        super.finalize();
        //tempfile.delete();
    }*/
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setGenre_list(String genre_list) {
        this.genre_list = genre_list;
    }

    public String getGenre_list() {
        return genre_list;
    }

    public int getMyVote() {
        return myVote;
    }

    public void setMyVote(int myVote) {
        this.myVote = myVote;
    }
}
