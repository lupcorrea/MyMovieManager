package comp.mymoviemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import comp.mymoviemanager.control.MovieSearchCtrl;
import comp.mymoviemanager.control.NewSearchCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MovieView;

public class MovieActivity extends Activity {

    ApplicationModel model;
    MovieView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_movie_main);


        model = ((MyMovieApplication) this.getApplication()).getModel();

        mainView = new MovieView(findViewById(R.id.movie_screen), model);
        MovieSearchCtrl ctrl = new MovieSearchCtrl(model, mainView);

    }

}
