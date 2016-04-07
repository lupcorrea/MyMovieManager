package comp.mymoviemanager.model;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Author: lupcorrea
 * Day: 01/04/16
 */

/* TODO: Set the api key. */
/* TODO: Build the constructor with dynamic data. */
/* TODO: Add the model access methods on demand. It's useless to build lots of methods now that will not be used in the last version. */
public class Profile {
    /* Profile attributes */
    private String name;
    private Bitmap profile_image;

    /* Movies lists */
    private LinkedList<Movie> topList = new LinkedList<>();
    private LinkedList<Movie> bottomList = new LinkedList<>();
    private LinkedList<Movie> toDoList = new LinkedList<>();

    public LinkedList<Movie> getTopList() {
        return topList;
    }

    public void setTopList(LinkedList<Movie> topList) {
        this.topList = topList;
    }

    public LinkedList<Movie> getBottomList() {
        return bottomList;
    }

    public void setBottomList(LinkedList<Movie> bottomList) {
        this.bottomList = bottomList;
    }

    public LinkedList<Movie> getToDoList() {
        return toDoList;
    }

    public void setToDoList(LinkedList<Movie> toDoList) {
        this.toDoList = toDoList;
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

    public void insertMovieToTopList (Movie m) {
        for (int index = 0; index < topList.size(); index++) {
            if (topList.get(index).getMyVote() < m.getMyVote()) continue;
            else topList.add(index, m);
        }
    }
}
