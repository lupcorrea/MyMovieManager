package comp.mymoviemanager;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import comp.mymoviemanager.control.SearchFindBtnCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.SearchResultsView;

/**
 * Created by Goldenberg on 05/04/16.
 */
public class ResultsActivity extends ToolBarActivity{
    ApplicationModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.view_search_result);

        model = ((MyMovieApplication) this.getApplication()).getModel();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText("Results");

        handleIntent(getIntent());

        SearchResultsView mainView = new SearchResultsView(findViewById(R.id.results_view), model);
        SearchFindBtnCtrl btnCtrl = new SearchFindBtnCtrl(model, mainView);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.view_search_result;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println(query);
            model.makeSearch(query);
        }
    }
}
