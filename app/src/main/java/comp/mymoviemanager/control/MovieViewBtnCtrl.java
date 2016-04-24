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

        changeVisual(null, model.getSelected());

    }

    public void changeVisual (View v, Movie m) {
        if (v == view.interested) {
            view.interested.setText("Added!");
            view.rate.setText("Rate");
            view.ninterested.setText("I'm not interested");
        } else if (v == view.ninterested) {
            view.ratingBarDisplayTitle.setText("Your rating for this movie:");
            view.ratingBarDisplay.setIsIndicator(false);
            view.ratingBarDisplay.setNumStars(m.getMyVote());
            view.ratingBarDisplay.setIsIndicator(true);
            view.interested.setText("I'm interested");
            view.rate.setText("Rate");
            view.ninterested.setText("Rated!");
        } else if (v == view.rate) {
            view.ratingBarDisplayTitle.setText("Your rating for this movie:");
            view.ratingBarDisplay.setIsIndicator(false);
            view.ratingBarDisplay.setNumStars(m.getMyVote());
            view.ratingBarDisplay.setIsIndicator(true);
            view.interested.setText("I'm interested");
            view.rate.setText("Rated!");
            view.ninterested.setText("I'm not interested");
        } else {
            // Check if movie already exists in database
            if (model.existsInTop(m) != null) {
                view.ratingBarDisplayTitle.setText("Your rating for this movie:");
                view.ratingBarDisplay.setIsIndicator(false);
                view.ratingBarDisplay.setNumStars(m.getMyVote());
                view.ratingBarDisplay.setIsIndicator(true);
                view.interested.setText("I'm interested");
                view.rate.setText("Rated!");
                view.ninterested.setText("I'm not interested");
            } else if (model.existsInBottom(m) != null) {
                view.ratingBarDisplayTitle.setText("Your rating for this movie:");
                view.ratingBarDisplay.setIsIndicator(false);
                view.ratingBarDisplay.setNumStars(m.getMyVote());
                view.ratingBarDisplay.setIsIndicator(true);
                view.interested.setText("I'm interested");
                view.rate.setText("Rate");
                view.ninterested.setText("Rated!");
            } else if (model.existsInFuture(m) != null) {
                view.interested.setText("Added!");
                view.rate.setText("Rate");
                view.ninterested.setText("I'm not interested");
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == view.interested){
            Movie m = model.addToFuture(model.getSelected(), db);
            System.err.println(m.getName());
            Toast.makeText(view.getContext(), m.getName() + " added to the list!", Toast.LENGTH_LONG);
            changeVisual(v, m);
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
                    model.getSelected().setMyVote(Math.round(rate.getRating()));
                    Movie m = model.addToHated(model.getSelected(), db);
                    System.err.println(m.getName());
                    Toast.makeText(view.getContext(), m.getMyVote() + " added to the list!", Toast.LENGTH_LONG).show();
                    changeVisual(v, m);
                    dialog.dismiss();
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
                    Toast.makeText(view.getContext(), m.getName() + " added to the list!", Toast.LENGTH_LONG);
                    changeVisual(v, m);
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
