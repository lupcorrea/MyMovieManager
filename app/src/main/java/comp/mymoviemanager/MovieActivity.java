package comp.mymoviemanager;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MovieView;

public class MovieActivity extends ToolBarActivity {

    ApplicationModel model;
    MovieView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.view_movie_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText("Movie Info");

        model = ((MyMovieApplication) this.getApplication()).getModel();

        mainView = new MovieView(findViewById(R.id.movie_screen), model);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.view_movie_main;
    }

}
