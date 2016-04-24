package comp.mymoviemanager.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.LinkedList;

import comp.mymoviemanager.model.Movie;

/**
 * Author: lupcorrea
 * Day: 11/04/16
 */

public class DatabaseManager extends SQLiteOpenHelper {
    // Constants
    /****************************************************************************/
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "myMoviesManager";

    // Table Names
    private static final String TABLE_PROFILES = "profiles";
    private static final String TABLE_LISTS = "lists";

    // Common Column names
    private static final String KEY_MAIL = "mail";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_GENRE = "genres_list";

    // Lists table columns
    private static final String KEY_TYPE = "belongs_to";
    private static final String KEY_RELEASE = "release";
    private static final String KEY_POPULARITY = "popularity";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_SINOPSIS = "sinopsis";
    private static final String KEY_VOTE = "personal_vote";
    private static final String KEY_ID = "id";

    // Table Create Statements
    // Profiles table
    private static final String CREATE_TABLE_PROFILES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PROFILES + "(" + KEY_MAIL + " TEXT PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_PHOTO + " TEXT," + KEY_GENRE
            + " TEXT);";

    // Lists table
    private static final String CREATE_TABLE_LISTS = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LISTS + "(" + KEY_MAIL + " TEXT," + KEY_TYPE + " INTEGER," + KEY_NAME
            + " TEXT," + KEY_PHOTO + " TEXT," + KEY_RELEASE
            + " TEXT," + KEY_GENRE + " TEXT," +  KEY_POPULARITY
            + " TEXT," + KEY_LANGUAGE + " TEXT," + KEY_SINOPSIS
            + " TEXT," + KEY_VOTE + " INTEGER," + KEY_ID + " INTEGER, FOREIGN KEY ("
            + KEY_MAIL + ") REFERENCES " + TABLE_PROFILES + "(" + KEY_MAIL + "));";

    // Database queries
    private final String selectFromLists = "SELECT * FROM " + TABLE_LISTS + " WHERE ";
    private final String selectFromProfiles = "SELECT * FROM " + TABLE_PROFILES + " WHERE ";
    /****************************************************************************/

    /* Singletons */
    private static DatabaseManager singleton;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseManager getInstance (Context context) {
        if (singleton == null) singleton = new DatabaseManager(context.getApplicationContext());
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_PROFILES);
        db.execSQL(CREATE_TABLE_LISTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables on upgrade (Still have to find out why, but it
        // apparently makes sense.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);

        // Create new tables
        onCreate(db);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /****************************************************************************/
    public Movie retrieveMovieFrom (String mail, String listType, Movie m) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Check if the movie exists
        String selectQuery = selectFromLists
                + KEY_ID + " =? AND "
                + KEY_MAIL + " =? AND "
                + KEY_TYPE + " =?";
        Log.e(LOG, selectQuery);

        Cursor c;
        try {
            c = db.rawQuery(selectQuery, new String[] {"" + m.getId(), mail, listType});
        } catch (Exception e) {
            return null;
        }
        c.moveToFirst();

        // Build a new object and return it
        // String name, String release, String popularity, String language, String sinopsis, String poster_path, Integer id, String genre_list
        return new Movie(c.getString(c.getColumnIndex(KEY_NAME)),
                c.getString(c.getColumnIndex(KEY_RELEASE)),
                c.getString(c.getColumnIndex(KEY_POPULARITY)),
                c.getString(c.getColumnIndex(KEY_LANGUAGE)),
                c.getString(c.getColumnIndex(KEY_SINOPSIS)),
                c.getString(c.getColumnIndex(KEY_PHOTO)),
                c.getInt(c.getColumnIndex(KEY_ID)),
                c.getString(c.getColumnIndex(KEY_GENRE)));
    }

    /****************************************************************************/
    public Movie addMovieTo (String mail, String listType, Movie m) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Check if the movie is already there
        String selectQuery = selectFromLists
                + KEY_ID + " =? AND "
                + KEY_MAIL + " =? AND "
                + KEY_TYPE + " =?";
        Log.e(LOG, selectQuery);

        try {
            Cursor c = db.rawQuery(selectQuery, new String[] {"" + m.getId(), mail, listType});
        } catch (Exception e) {
            return null;
        }

        ContentValues values = new ContentValues();
        values.put(KEY_MAIL, mail);
        values.put(KEY_TYPE, listType);
        values.put(KEY_NAME, m.getName());
        values.put(KEY_PHOTO, m.getPosterPath());
        values.put(KEY_RELEASE, m.getRelease());
        values.put(KEY_GENRE, m.getGenre_list());
        values.put(KEY_POPULARITY, m.getPopularity());
        values.put(KEY_LANGUAGE, m.getLanguage());
        values.put(KEY_SINOPSIS, m.getSinopsis());
        values.put(KEY_VOTE, m.getMyVote());
        values.put(KEY_ID, m.getId());

        try {
            db.beginTransaction();
            db.insert(TABLE_LISTS, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return retrieveMovieFrom(mail, listType, m);
    }

    /****************************************************************************/
    public void deleteMovieFrom(String mail, String listType, Movie m) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction();
            db.delete(TABLE_LISTS, KEY_ID + " = " + m.getId() + " AND "
                    + KEY_MAIL + " = " + mail + " AND "
                    + KEY_TYPE + " = " + listType, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /****************************************************************************/
    public LinkedList createListFromDb (String mail, String listType) {
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedList<Movie> ll_movie = new LinkedList();
        Movie m;
        String selectQuery = selectFromLists
                + KEY_MAIL + " =? AND "
                + KEY_TYPE + " =?";
        Log.e(LOG, selectQuery);

        Cursor c;
        try {
            c = db.rawQuery(selectQuery, new String[] {"" + mail, listType});
        } catch (Exception e) {
            return null;
        }
        c.moveToFirst();

        // Build a new object and return it
        // String name, String release, String popularity, String language, String sinopsis, String poster_path, Integer id, String genre_list
        while (!c.isAfterLast()) {
            m = new Movie(c.getString(c.getColumnIndex(KEY_NAME)),
                    c.getString(c.getColumnIndex(KEY_RELEASE)),
                    c.getString(c.getColumnIndex(KEY_POPULARITY)),
                    c.getString(c.getColumnIndex(KEY_LANGUAGE)),
                    c.getString(c.getColumnIndex(KEY_SINOPSIS)),
                    c.getString(c.getColumnIndex(KEY_PHOTO)),
                    c.getInt(c.getColumnIndex(KEY_ID)),
                    c.getString(c.getColumnIndex(KEY_GENRE)));
            m.setMyVote(c.getInt(c.getColumnIndex(KEY_VOTE)));
            if (ll_movie.size() == 0) {
                ll_movie.add(m);
                c.moveToNext();
                continue;
            }
            for (int i = 0; i < ll_movie.size(); i++) {
                if (ll_movie.get(i).getMyVote() < m.getMyVote()) ll_movie.add(i, m);
            }
            c.moveToNext();
        }
        return ll_movie;
    }

}
