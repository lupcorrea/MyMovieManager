package comp.mymoviemanager.control;

import android.content.Intent;
import android.view.View;
import android.widget.SearchView;

import comp.mymoviemanager.ResultsActivity;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MovieView;
import comp.mymoviemanager.view.SearchResultsView;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class MovieSearchCtrl implements SearchView.OnQueryTextListener{
    ApplicationModel model;
    MovieView view;

    public MovieSearchCtrl(ApplicationModel model, MovieView view){
        this.model = model;
        this.view = view;

        view.search.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent i = new Intent(view.getContext(), ResultsActivity.class);
        view.getContext().startActivity(i);
        model.makeSearch(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
