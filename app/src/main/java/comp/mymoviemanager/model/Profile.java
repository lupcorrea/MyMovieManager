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

    public static synchronized Profile getInstance() {
        if (singleton == null) singleton = new Profile();
        return singleton;
    }
    /****************************************************************************/

    /****************************************************************************/
    public void addToTopList(Movie m, DatabaseManager db) {
        if (existsIn(m, "topList") == null) topList.add(m);
        db.addMovieTo(mail, "topList", m);
    }

    public void addToFutureList(Movie m, DatabaseManager db) {
        if (existsIn(m, "futureList") == null) futureList.add(m);
        db.addMovieTo(mail, "futureList", m);
    }

    /****************************************************************************/
    public void removeFrom(Movie m, DatabaseManager db, String listType) {
        switch (listType) {
            case "topList":
                db.deleteMovieFrom(mail, "topList", m);
                topList.remove(m);
                break;
            case "futureList":
                db.deleteMovieFrom(mail, "futureList", m);
                futureList.remove(m);
                break;
        }
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
    public Movie existsIn(Movie m, String listType) {
        switch (listType) {
            case "topList":
                for (int i = 0; i < topList.size(); i++) {
                    if (m.getId().intValue() == topList.get(i).getId().intValue())
                        return topList.get(i);
                }
                break;
            case "futureList":
                for (int i = 0; i < futureList.size(); i++) {
                    if (m.getId() == futureList.get(i).getId()) return futureList.get(i);
                }
                break;
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}