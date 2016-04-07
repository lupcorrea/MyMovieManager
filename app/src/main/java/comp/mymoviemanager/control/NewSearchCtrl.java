package comp.mymoviemanager.control;

import android.view.View;
import android.widget.SearchView;

import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.SearchResultsView;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class NewSearchCtrl implements SearchView.OnQueryTextListener{
    ApplicationModel model;
    SearchResultsView view;

    public NewSearchCtrl(ApplicationModel model, SearchResultsView view){
        this.model = model;
        this.view = view;

        view.search.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        view.progressBar.setVisibility(View.VISIBLE);
        model.makeSearch(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
