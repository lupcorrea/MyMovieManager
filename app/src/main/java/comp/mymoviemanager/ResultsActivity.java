package comp.mymoviemanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SearchView;

import comp.mymoviemanager.control.NewSearchCtrl;
import comp.mymoviemanager.control.SearchFindCtrl;
import comp.mymoviemanager.model.MovieModel;
import comp.mymoviemanager.view.SearchResultsView;

/**
 * Created by Goldenberg on 05/04/16.
 */
public class ResultsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        MovieModel model = ((MyMovieApplication) this.getApplication()).getModel();

        SearchResultsView mainView = new SearchResultsView(findViewById(R.id.results_view), model);
        NewSearchCtrl ctrl = new NewSearchCtrl(model, mainView);
    }
}
