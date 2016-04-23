package comp.mymoviemanager.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.ContactsContract;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import comp.mymoviemanager.util.DatabaseManager;

import static android.database.sqlite.SQLiteDatabase.OPEN_READWRITE;

/**
 * Author: lupcorrea
 * Day: 01/04/16
 */

public class Profile {
    /* Profile attributes */
    private String name = "Fgh";
    private String profile_image_path;
    private Bitmap profile_image;
    private String mail = "www";

    /* Movies lists */
    private LinkedList<Movie> topList = new LinkedList<>();
    private LinkedList<Movie> bottomList = new LinkedList<>();
    private LinkedList<Movie> futureList = new LinkedList<>();

    /* Singleton */
    private static Profile singleton;

    public static synchronized Profile getInstance () {
        if (singleton == null) singleton = new Profile();
        return singleton;
    }

    // Adding methods
    public void addToTopList (Movie m, DatabaseManager db) {
        Movie newMovie = db.addMovieTo(mail, "topList", m);
        if (newMovie != null) topList.add(newMovie);
    }
    public void addToBottomList (Movie m, DatabaseManager db) {
        Movie newMovie = db.addMovieTo(mail, "bottomList", m);
        if (newMovie != null) bottomList.add(newMovie);
    }
    public void addToFutureList (Movie m, DatabaseManager db) {
        Movie newMovie = db.addMovieTo(mail, "futureList", m);
        if (newMovie != null) futureList.add(newMovie);
    }

    // Removal methods
    public void removeFromTopList (Movie m, DatabaseManager db) {
        db.deleteMovieFrom(mail, "topList", m);
        topList.remove(m);
    }
    public void removeFromBottomList (Movie m, DatabaseManager db) {
        db.deleteMovieFrom(mail, "bottomList", m);
        bottomList.remove(m);
    }
    public void removeFromFutureList (Movie m, DatabaseManager db) {
        db.deleteMovieFrom(mail, "futureList", m);
        futureList.remove(m);
    }

    // Getters
    public LinkedList<Movie> getTopList() {
        return topList;
    }
    public LinkedList<Movie> getBottomList() {
        return bottomList;
    }
    public LinkedList<Movie> getFutureList() {
        return futureList;
    }
}