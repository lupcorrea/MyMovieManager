package comp.mymoviemanager.control;

import android.view.View;
import android.widget.Toast;

import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MovieView;

/**
 * Created by Goldenberg on 17/04/16.
 */
public class MovieViewBtnCtrl implements View.OnClickListener{
    ApplicationModel model;
    MovieView view;

    public MovieViewBtnCtrl(ApplicationModel model, MovieView view){
        this.model = model;
        this.view = view;

        view.interested.setOnClickListener(this);
        view.ninterested.setOnClickListener(this);
        view.rate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == view.interested){
            //
        }
        else if (v == view.ninterested){
            //
        }
        else if (v == view.rate){
            //
        }
    }
}
