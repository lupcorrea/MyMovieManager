package comp.mymoviemanager;

import android.app.Activity;
import android.os.Bundle;

import comp.mymoviemanager.model.MovieModel;
import comp.mymoviemanager.view.MovieView;

public class MovieActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_movie_main);


        MovieModel model = ((MyMovieApplication) this.getApplication()).getModel();

        MovieView mainView = new MovieView(findViewById(R.id.movie_screen), model);
        //SearchFindCtrl ctrl = new SearchFindCtrl(model, mainView);

    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
