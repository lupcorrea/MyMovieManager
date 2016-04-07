package comp.mymoviemanager.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static android.database.sqlite.SQLiteDatabase.OPEN_READWRITE;

/**
 * Author: lupcorrea
 * Day: 01/04/16
 */

public class Profile {
    /* Profile attributes */
    private String name;
    private Bitmap profile_image;

    /* Movies lists */
    private LinkedList<Movie> topList = new LinkedList<>();
    private LinkedList<Movie> bottomList = new LinkedList<>();
    private LinkedList<Movie> futureList = new LinkedList<>();

    /* Database */
    private SQLiteDatabase database;

    /* Constructor */
    public Profile (String name) {
        this.name = name;

        /* Database construction */
        database = SQLiteDatabase.openOrCreateDatabase(name, null, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS " + name + "(Mail TEXT PRIMARY KEY NOT NULL, Name TEXT NOT NULL, TopList TEXT, BottomList TEXT, FutureList TEXT);");
        database.execSQL("CREATE TABLE IF NOT EXISTS TopList (Id INT PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Release TEXT, GenreList TEXT, Popularity TEXT, Language TEXT, Sinopsis TEXT, PosterPath TEXT, PersonalVote INT);");
        database.execSQL("CREATE TABLE IF NOT EXISTS BottomList (Id INT PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Release TEXT, GenreList TEXT, Popularity TEXT, Language TEXT, Sinopsis TEXT, PosterPath TEXT, PersonalVote INT);");
        database.execSQL("CREATE TABLE IF NOT EXISTS FutureList (Id INT PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Release TEXT, GenreList TEXT, Popularity TEXT, Language TEXT, Sinopsis TEXT, PosterPath TEXT, PersonalVote INT);");
    }

    public LinkedList<Movie> getTopList() {
        Movie m = null;

        Cursor resultSet = database.rawQuery("Select * from TopList", null);
        resultSet.moveToFirst();

        for (int i = 0; i < 10; i++) {
            m.setId(resultSet.getInt(1));
            m.setName(resultSet.getString(2));
            m.setRelease(resultSet.getString(3));
            m.setGenre_list(resultSet.getString(4));
            m.setPopularity(resultSet.getString(5));
            m.setLanguage(resultSet.getString(6));
            m.setSinopsis(resultSet.getString(7));
            m.setPosterPath(resultSet.getString(8));
            m.setMyVote(resultSet.getInt(9));

            this.topList.set(i, m);
            resultSet.moveToNext();
        }

        return this.topList;
    }

    public void setTopList(LinkedList<Movie> topList) {
        Cursor resultSet = database.rawQuery("Select * from TopList", null);
        resultSet.moveToFirst();

        for (int i = 0; i < this.topList.size(); i++) {

        }

        this.topList = topList;
    }

    public LinkedList<Movie> getBottomList() {
        return bottomList;
    }

    public void setBottomList(LinkedList<Movie> bottomList) {
        this.bottomList = bottomList;
    }

    public LinkedList<Movie> getFutureList() {
        return futureList;
    }

    public void setFutureList(LinkedList<Movie> toDoList) {
        this.futureList = toDoList;
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

    public void addMovieToTopList (Movie m) {
        for (int index = 0; index < topList.size(); index++) {
            if (topList.get(index).getMyVote() < m.getMyVote()) continue;
            else topList.add(index, m);
        }
    }

    public void addMovieToBottomList (Movie m) {
        for (int index = 0; index < bottomList.size(); index++) {
            if (bottomList.get(index).getMyVote() > m.getMyVote()) continue;
            else bottomList.add(index, m);
        }
    }

    public void addMovieToFutureList (Movie m) {
        futureList.add(m);
    }
}
