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

    public LinkedList<Movie> getTopList(DatabaseManager joe) {
        topList = joe.getMoviesFrom(this.mail, "TopList");

        return this.topList;
    }

    public void setTopList(DatabaseManager joe) {
        joe.updateMoviesAt(this.mail, "TopList", this.topList);
        joe.closeDB();
    }

    public LinkedList<Movie> getBottomList(DatabaseManager joe) {
        bottomList = joe.getMoviesFrom(this.mail, "BottomList");
        joe.closeDB();

        return bottomList;
    }

    public void setBottomList(DatabaseManager joe) {
        joe.updateMoviesAt(this.mail, "BottomList", this.bottomList);
        joe.closeDB();
    }

    public LinkedList<Movie> getFutureList(DatabaseManager joe) {
        futureList = joe.getMoviesFrom(this.mail, "FutureList");
        joe.closeDB();

        return futureList;
    }

    public void setFutureList(DatabaseManager joe) {
        joe.updateMoviesAt(this.mail,"FutureList", this.futureList);
        joe.closeDB();
    }

    public void addMovieToTopList(Movie m) {
        for (int index = 0; index < topList.size(); index++) {
            if (topList.get(index).getMyVote() < m.getMyVote()) continue;
            else {
                topList.add(index, m);
                break;
            }
        }
    }

    public void addMovieToBottomList (Movie m) {
        for (int index = 0; index < bottomList.size(); index++) {
            if (bottomList.get(index).getMyVote() > m.getMyVote()) continue;
            else {
                bottomList.add(index, m);
                break;
            }
        }
    }

    public void addMovieToFutureList (Movie m) {
        futureList.add(m);
    }

    public Bitmap getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Bitmap profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
