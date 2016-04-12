package comp.mymoviemanager.control;

import android.content.Intent;
import android.widget.SearchView;

import comp.mymoviemanager.ResultsActivity;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.ProfileView;

/**
 * Created by Goldenberg on 10/04/16.
 */
public class ProfileSearchCtrl implements SearchView.OnQueryTextListener{
    ApplicationModel model;
    ProfileView view;

    public ProfileSearchCtrl(ApplicationModel model, ProfileView view){
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
