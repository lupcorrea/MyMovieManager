package comp.mymoviemanager.control;

import android.view.View;
import android.widget.Toast;

import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.util.DatabaseManager;
import comp.mymoviemanager.view.MovieView;

/**
 * Created by Goldenberg on 17/04/16.
 */
public class MovieViewBtnCtrl implements View.OnClickListener{
    ApplicationModel model;
    MovieView view;
    DatabaseManager db;

    public MovieViewBtnCtrl(ApplicationModel model, MovieView view, DatabaseManager db){
        this.model = model;
        this.view = view;
        this.db = db;

        view.interested.setOnClickListener(this);
        view.ninterested.setOnClickListener(this);
        view.rate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == view.interested){
            Movie m = model.addToFuture(model.getSelected(), db);
            System.err.println(m.getName());
        }
        else if (v == view.ninterested){
            Movie m = model.addToHated(model.getSelected(), db);
            System.err.println(m.getName());
        }
        else if (v == view.rate){
            Movie m = model.addToLiked(model.getSelected(), db);
            System.err.println(m.getName());
        }
    }
}
