package comp.mymoviemanager;

import android.app.Activity;
import android.os.Bundle;

import comp.mymoviemanager.control.MoviePreferencesBtnCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MoviePreferencesView;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class MoviePreferencesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_personalize_main);

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        MoviePreferencesView view = new MoviePreferencesView(findViewById(R.id.personalize), model);
        MoviePreferencesBtnCtrl ctrl = new MoviePreferencesBtnCtrl(view, model);
    }
}
