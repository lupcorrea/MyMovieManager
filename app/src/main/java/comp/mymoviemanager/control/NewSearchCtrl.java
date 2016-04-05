package comp.mymoviemanager.control;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SearchView;

import comp.mymoviemanager.ResultsActivity;
import comp.mymoviemanager.model.MovieModel;
import comp.mymoviemanager.view.MainSearchView;
import comp.mymoviemanager.view.SearchResultsView;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class NewSearchCtrl implements SearchView.OnQueryTextListener{
    MovieModel model;
    SearchResultsView view;

    public NewSearchCtrl(MovieModel model, SearchResultsView view){
        this.model = model;
        this.view = view;

        view.search.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        model.makeSearch(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
