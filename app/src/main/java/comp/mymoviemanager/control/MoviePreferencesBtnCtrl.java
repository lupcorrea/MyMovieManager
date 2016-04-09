package comp.mymoviemanager.control;

import android.content.Intent;
import android.view.View;

import comp.mymoviemanager.ProfileActivity;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MoviePreferencesView;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class MoviePreferencesBtnCtrl implements View.OnClickListener{
    MoviePreferencesView view;
    ApplicationModel model;

    public MoviePreferencesBtnCtrl(MoviePreferencesView view, ApplicationModel model){
        this.view = view;
        this.model = model;

        view.skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(view.getContext(), ProfileActivity.class);
        view.getContext().startActivity(i);
    }
}
