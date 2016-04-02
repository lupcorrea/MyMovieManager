package comp.mymoviemanager;

import android.app.Application;
import comp.mymoviemanager.model.ProfileModel;
import comp.mymoviemanager.model.Movie;

public class MyMovieApplication extends Application {
    private ProfileModel profile_model = new ProfileModel();
    private Movie movie_model = new Movie();

    public ProfileModel getProfile_model() { return profile_model; }
    public Movie getMovie_model() { return movie_model; }

    public void setProfile_model(ProfileModel model) {
        this.profile_model = model;
    }

    public void setMovie_model(Movie model) {
        this.movie_model = model;
    }
}
