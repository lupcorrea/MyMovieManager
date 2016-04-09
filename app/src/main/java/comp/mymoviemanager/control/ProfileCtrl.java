package comp.mymoviemanager.control;

import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.Profile;

/**
 * Author: lupcorrea
 * Day: 09/04/16
 */
public class ProfileCtrl {

    public void addToLiked (Profile p, Movie m) {
        /* Add movie to linked list and then updates DB. */
        p.addMovieToTopList(m);
        p.setTopList();
    }

    public void addToHated (Profile p, Movie m) {
        /* Add movie to linked list and then updates DB. */
        p.addMovieToBottomList(m);
        p.setBottomList();
    }

    public void addToFuture (Profile p, Movie m) {
        /* Add movie to linked list and then updates DB. */
        p.addMovieToFutureList(m);
        p.setFutureList();
    }
}
