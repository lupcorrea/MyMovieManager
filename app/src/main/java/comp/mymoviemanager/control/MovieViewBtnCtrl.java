package comp.mymoviemanager.control;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import comp.mymoviemanager.R;
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
            // Create the popup
            final Dialog dialog = new Dialog (view.getContext());
            dialog.setContentView(R.layout.popup_rate);
            dialog.setTitle("Rate this movie!");
            final RatingBar rate = (RatingBar) dialog.findViewById(R.id.ratingBar);
            Button confirmBtn = (Button) dialog.findViewById(R.id.confirmButton);
            Button dismissBtn = (Button) dialog.findViewById(R.id.dismissButton);

            // Customize the popup
            ((TextView) dialog.findViewById(R.id.rating_popup_title)).setText(model.getSelected().getName());

            // Show the popup
            dialog.show();

            // Set listeners
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.getSelected().setMyVote((int) rate.getRating());
                    Movie m = model.addToHated(model.getSelected(), db);
                    System.err.println(m.getName());
                    dialog.dismiss();
                    // Insert toast here
                    // Insert visual change of the button here
                }
            });
            dismissBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        else if (v == view.rate) {
            // Create the popup
            final Dialog dialog = new Dialog (view.getContext());
            dialog.setContentView(R.layout.popup_rate);
            dialog.setTitle("Rate this movie!");
            final RatingBar rate = (RatingBar) dialog.findViewById(R.id.ratingBar);
            Button confirmBtn = (Button) dialog.findViewById(R.id.confirmButton);
            Button dismissBtn = (Button) dialog.findViewById(R.id.dismissButton);

            // Customize the popup
            ((TextView) dialog.findViewById(R.id.rating_popup_title)).setText(model.getSelected().getName());

            // Show the popup
            dialog.show();

            // Set listeners
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.getSelected().setMyVote((int) rate.getRating());
                    Movie m = model.addToLiked(model.getSelected(), db);
                    System.err.println(m.getName());
                    dialog.dismiss();
                    // Insert toast here
                    // Insert visual change of the button here
                }
            });
            dismissBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }
}
