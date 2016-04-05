package comp.mymoviemanager;

import android.app.Activity;
import android.os.Bundle;

import comp.mymoviemanager.control.SearchFindCtrl;
import comp.mymoviemanager.model.MovieModel;
import comp.mymoviemanager.view.MainSearchView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        MovieModel model = ((MyMovieApplication) this.getApplication()).getModel();

        MainSearchView mainView = new MainSearchView(findViewById(R.id.main_search), model);
        SearchFindCtrl ctrl = new SearchFindCtrl(model, mainView);
    }

}
