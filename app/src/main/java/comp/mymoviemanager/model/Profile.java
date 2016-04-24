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
    /****************************************************************************/
    /* Profile attributes */
    private String name = "Fgh";
    private String profile_image_path;
    private Bitmap profile_image;
    private String mail = "www";

    /* Movies lists */
    private LinkedList<Movie> topList = new LinkedList<>();
    private LinkedList<Movie> futureList = new LinkedList<>();

    /****************************************************************************/
    /* Singleton */
    private static Profile singleton;

    public static synchronized Profile getInstance () {
        if (singleton == null) singleton = new Profile();
        return singleton;
    }
    /****************************************************************************/

    /****************************************************************************/
    public void addToTopList (Movie m, DatabaseManager db) {
        if (existsIn(m, "topList")) {
            return;
        } else if (existsIn(m, "futureList")) {
            removeFromFutureList(m, db);
        }

        Movie newMovie = db.addMovieTo(mail, "topList", m);
        if (newMovie != null) topList.add(newMovie);
    }
    public void addToFutureList (Movie m, DatabaseManager db) {
        if (existsIn(m, "futureList")) {
            return;
        } else if (existsIn(m, "topList")) {
            removeFromTopList(m, db);
        }

        Movie newMovie = db.addMovieTo(mail, "futureList", m);
        if (newMovie != null) futureList.add(newMovie);
    }

    /****************************************************************************/
    public void removeFromTopList (Movie m, DatabaseManager db) {
        db.deleteMovieFrom(mail, "topList", m);
        topList.remove(m);
    }
    public void removeFromFutureList (Movie m, DatabaseManager db) {
        db.deleteMovieFrom(mail, "futureList", m);
        futureList.remove(m);
    }

    /****************************************************************************/
    public LinkedList<Movie> getTopList() {
        return topList;
    }
    public LinkedList<Movie> getFutureList() {
        return futureList;
    }
    public String getMail() {
        return mail;
    }
    public void setTopList(LinkedList<Movie> topList) {
        this.topList = topList;
    }
    public void setFutureList(LinkedList<Movie> futureList) {
        this.futureList = futureList;
    }

    /****************************************************************************/
    public boolean existsIn (Movie m, String listType) {
        switch (listType) {
            case "topList":
                for (int i = 0; i < topList.size(); i++) {
                    if (m.getId().intValue() == topList.get(i).getId().intValue()) return true;
                }
                break;
            case "futureList":
                for (int i = 0; i < futureList.size(); i++) {
                    if (m.getId() == futureList.get(i).getId()) return true;
                }
                break;
        }

        return false;
    }

    /****************************************************************************/
    public Movie existsInTop (Movie m) {
        if (topList != null) {
            for (int i = 0; i < topList.size(); i++) {
                if (topList.get(i) != null) System.err.println ("[" + topList.get(i).getId() + "] " + topList.get(i).getName());
                if (topList.get(i).getId() == m.getId()) return topList.get(i);
            }
        }

        return null;
    }
}