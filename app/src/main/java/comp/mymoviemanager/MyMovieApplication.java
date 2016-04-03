package comp.mymoviemanager;

import android.app.Application;

import comp.mymoviemanager.model.MovieModel;

public class MyMovieApplication extends Application {
    private MovieModel model = new MovieModel();
    public MovieModel getModel() { return model; }

    public void setModel(MovieModel model) {
        this.model = model;
    }
}
