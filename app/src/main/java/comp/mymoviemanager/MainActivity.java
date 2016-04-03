package comp.mymoviemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import comp.mymoviemanager.control.SearchButtonCtrl;
import comp.mymoviemanager.model.MovieModel;
import comp.mymoviemanager.view.SearchView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        MovieModel model = ((MyMovieApplication) this.getApplication()).getModel();

        SearchView mainView = new SearchView(findViewById(R.id.main_search), model);
        SearchButtonCtrl ctrl = new SearchButtonCtrl(model, mainView);
    }

}
