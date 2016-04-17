package comp.mymoviemanager.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
            + " TEXT," + KEY_VOTE + " TEXT," + KEY_ID + " INTEGER, FOREIGN KEY ("
            + KEY_MAIL + ") REFERENCES " + TABLE_PROFILES + "(" + KEY_MAIL + "));";

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

    public LinkedList<Movie> getMoviesFrom (String mail, String listType) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_LISTS + " WHERE " + KEY_TYPE + " = "
                + listType + " AND " + KEY_MAIL + " = " + mail;

        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) c.moveToFirst();

        LinkedList<Movie> ll_movies = new LinkedList<>();
        do {
            Movie m = new Movie(c.getString(c.getColumnIndex(KEY_NAME)), c.getString(c.getColumnIndex(KEY_RELEASE)), c.getString(c.getColumnIndex(KEY_POPULARITY)), c.getString(c.getColumnIndex(KEY_LANGUAGE)), c.getString(c.getColumnIndex(KEY_SINOPSIS)), c.getString(c.getColumnIndex(KEY_PHOTO)), c.getInt(c.getColumnIndex(KEY_ID)), c.getString(c.getColumnIndex(KEY_GENRE)));
            m.setMyVote(c.getInt(c.getColumnIndex(KEY_VOTE)));
            ll_movies.add(m);
            c.moveToNext();
        } while (!c.isAfterLast());

        return ll_movies;
    }

    public void updateMoviesAt(String mail, String listType, LinkedList<Movie> ll_movies) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LISTS, KEY_MAIL + " = " + mail + " AND " + KEY_TYPE + " = " + listType, null);

        ContentValues values = new ContentValues();
        for (int i = 0; i < ll_movies.size(); i++) {
            values.put(KEY_MAIL, mail);
            values.put(KEY_TYPE, listType);
            values.put(KEY_NAME, ll_movies.get(i).getName());
            values.put(KEY_PHOTO, ll_movies.get(i).getPosterPath());
            values.put(KEY_RELEASE, ll_movies.get(i).getRelease());
            values.put(KEY_GENRE, ll_movies.get(i).getGenre_list());
            values.put(KEY_POPULARITY, ll_movies.get(i).getPopularity());
            values.put(KEY_LANGUAGE, ll_movies.get(i).getLanguage());
            values.put(KEY_SINOPSIS, ll_movies.get(i).getSinopsis());
            values.put(KEY_VOTE, ll_movies.get(i).getMyVote());
            values.put(KEY_ID, ll_movies.get(i).getId());

            db.insert(TABLE_LISTS, null, values);
        }
    }

}
