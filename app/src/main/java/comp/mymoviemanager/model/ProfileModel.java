package comp.mymoviemanager.model;

import java.util.LinkedList;

/**
 * Author: lupcorrea
 * Day: 01/04/16
 */

/* TODO: Set the api key. */
/* TODO: Instantiate the profile image. */
/* TODO: Build the constructor with dynamic data. */
/* TODO: Add the model access methods on demand. It's useless to build lots of methods now that will not be used in the last version. */
public class ProfileModel {
    /* Profile attributes */
    private String name;
    private final String api_key = "ff";

    /* Movies lists */
    private LinkedList<Movie> topList = new LinkedList<>();
    private LinkedList<Movie> bottomList = new LinkedList<>();
    private LinkedList<Movie> toDoList = new LinkedList<>();

    /* Constructor */
    public ProfileModel() {

    }
}
