package comp.mymoviemanager.model;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

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
            if (bottomList.get(index).getMyVote() < m.getMyVote()) continue;
            else bottomList.add(index, m);
        }
    }

    public void addMovieToFutureList (Movie m) {
        for (int index = 0; index < futureList.size(); index++) {
            if (futureList.get(index).getMyVote() < m.getMyVote()) continue;
            else futureList.add(index, m);
        }
    }
}
