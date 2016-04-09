package comp.mymoviemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

        ImageView home = (ImageView) findViewById(R.id.home_action);
        ImageView profile = (ImageView) findViewById(R.id.profile_action);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }

}
