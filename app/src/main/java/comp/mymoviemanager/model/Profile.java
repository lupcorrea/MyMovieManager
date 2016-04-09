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
        database.execSQL("CREATE TABLE IF NOT EXISTS TopList (Id INT PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Release TEXT, GenreList TEXT, Popularity TEXT, Language TEXT, Sinopsis TEXT, PosterPath TEXT, PersonalVote INT, PersonalRank INT NOT NULL UNIQUE);");
        database.execSQL("CREATE TABLE IF NOT EXISTS BottomList (Id INT PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Release TEXT, GenreList TEXT, Popularity TEXT, Language TEXT, Sinopsis TEXT, PosterPath TEXT, PersonalVote INT, PersonalRank INT NOT NULL UNIQUE);");
        database.execSQL("CREATE TABLE IF NOT EXISTS FutureList (Id INT PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Release TEXT, GenreList TEXT, Popularity TEXT, Language TEXT, Sinopsis TEXT, PosterPath TEXT, PersonalVote INT, PersonalRank INT NOT NULL UNIQUE);");
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

    public void setTopList() {
        for (int i = 0; i < this.topList.size(); i++) {
            database.rawQuery("UPDATE TopList SET Id = \'" + this.topList.get(i).getId() + "\' WHERE RANK = " + i + 1 + ";", null);
            database.rawQuery("UPDATE TopList SET Name = \'" + this.topList.get(i).getName() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE TopList SET Release = \'" + this.topList.get(i).getRelease() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE TopList SET GenreList = \'" + this.topList.get(i).getGenre_list() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE TopList SET Popularity = \'" + this.topList.get(i).getPopularity() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE TopList SET Language = \'" + this.topList.get(i).getLanguage() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE TopList SET Sinopsis = \'" + this.topList.get(i).getSinopsis() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE TopList SET PosterPath = \'" + this.topList.get(i).getPosterPath() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE TopList SET PersonalVote = \'" + this.topList.get(i).getMyVote() + "\' WHERE RANK = " + i+1 + ";", null);
        }
    }

    public LinkedList<Movie> getBottomList() {
        Movie m = null;

        Cursor resultSet = database.rawQuery("Select * from BottomList", null);
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

            this.bottomList.set(i, m);
            resultSet.moveToNext();
        }

        return bottomList;
    }

    public void setBottomList() {
        for (int i = 0; i < this.bottomList.size(); i++) {
            database.rawQuery("UPDATE BottomList SET Id = \'" + this.bottomList.get(i).getId() + "\' WHERE RANK = " + i + 1 + ";", null);
            database.rawQuery("UPDATE BottomList SET Name = \'" + this.bottomList.get(i).getName() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE BottomList SET Release = \'" + this.bottomList.get(i).getRelease() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE BottomList SET GenreList = \'" + this.bottomList.get(i).getGenre_list() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE BottomList SET Popularity = \'" + this.bottomList.get(i).getPopularity() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE BottomList SET Language = \'" + this.bottomList.get(i).getLanguage() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE BottomList SET Sinopsis = \'" + this.bottomList.get(i).getSinopsis() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE BottomList SET PosterPath = \'" + this.bottomList.get(i).getPosterPath() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE BottomList SET PersonalVote = \'" + this.bottomList.get(i).getMyVote() + "\' WHERE RANK = " + i+1 + ";", null);
        }
    }

    public LinkedList<Movie> getFutureList() {
        Movie m = null;

        Cursor resultSet = database.rawQuery("Select * from FutureList", null);
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

            this.futureList.set(i, m);
            resultSet.moveToNext();
        }

        return futureList;
    }

    public void setFutureList() {
        for (int i = 0; i < this.futureList.size(); i++) {
            database.rawQuery("UPDATE FutureList SET Id = \'" + this.futureList.get(i).getId() + "\' WHERE RANK = " + i + 1 + ";", null);
            database.rawQuery("UPDATE FutureList SET Name = \'" + this.futureList.get(i).getName() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE FutureList SET Release = \'" + this.futureList.get(i).getRelease() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE FutureList SET GenreList = \'" + this.futureList.get(i).getGenre_list() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE FutureList SET Popularity = \'" + this.futureList.get(i).getPopularity() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE FutureList SET Language = \'" + this.futureList.get(i).getLanguage() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE FutureList SET Sinopsis = \'" + this.futureList.get(i).getSinopsis() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE FutureList SET PosterPath = \'" + this.futureList.get(i).getPosterPath() + "\' WHERE RANK = " + i+1 + ";", null);
            database.rawQuery("UPDATE FutureList SET PersonalVote = \'" + this.futureList.get(i).getMyVote() + "\' WHERE RANK = " + i+1 + ";", null);
        }
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
