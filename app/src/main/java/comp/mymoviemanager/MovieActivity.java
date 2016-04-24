package comp.mymoviemanager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import comp.mymoviemanager.control.MovieViewBtnCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.model.Profile;
import comp.mymoviemanager.util.DatabaseManager;
import comp.mymoviemanager.view.MovieView;

public class MovieActivity extends ToolBarActivity {

    ApplicationModel model;
    MovieView mainView;
    DatabaseManager db;

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
        db = DatabaseManager.getInstance(getApplicationContext());

        mainView = new MovieView(findViewById(R.id.movie_screen), model);
        MovieViewBtnCtrl ctrl = new MovieViewBtnCtrl(model, mainView, db);


        /* Customization of rating bar */
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar_main);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.view_movie_main;
    }

}
