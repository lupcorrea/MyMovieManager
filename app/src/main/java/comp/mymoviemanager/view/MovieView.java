package comp.mymoviemanager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Rating;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.ApplicationModel;

/**
 * Created by Goldenberg on 06/04/16.
 */
public class MovieView implements Observer{
    ApplicationModel model;
    View view;
    Movie selected = null;
    public Button rate, interested, ninterested;
    public RatingBar ratingBarDisplay;
    public TextView ratingBarDisplayTitle;

    public MovieView(View view, ApplicationModel model){
        this.model = model;
        this.view = view;
        this.selected = model.getSelected();

        model.addObserver(this);

        ImageView poster = (ImageView) view.findViewById(R.id.movie_thumbnail);
        TextView movie_name = (TextView) view.findViewById(R.id.movie_title);
        TextView movie_release = (TextView) view.findViewById(R.id.movie_year);
        TextView movie_genre = (TextView) view.findViewById(R.id.movie_tag);
        TextView movie_description = (TextView) view.findViewById(R.id.movie_plot);

        ratingBarDisplay = (RatingBar) view.findViewById(R.id.ratingBar_main);
        ratingBarDisplayTitle = (TextView) view.findViewById(R.id.rating_label);

        Movie m = model.getSelected();
        rate = (Button) view.findViewById(R.id.rate_btn);
        interested = (Button) view.findViewById(R.id.interested_btn);
        ninterested = (Button) view.findViewById(R.id.not_interested_btn);
        //Set the button's text
        if (model.existsInTop(m) != null) {
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setIsIndicator(false);
            ratingBarDisplay.setNumStars(m.getMyVote());
            ratingBarDisplay.setIsIndicator(true);
            interested.setText("I'm interested");
            rate.setText("Rated!");
            ninterested.setText("I'm not interested");
        } else if (model.existsInBottom(m) != null) {
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setIsIndicator(false);
            ratingBarDisplay.setNumStars(m.getMyVote());
            ratingBarDisplay.setIsIndicator(true);
            interested.setText("I'm interested");
            rate.setText("Rate");
            ninterested.setText("Rated!");
        } else if (model.existsInFuture(m) != null) {
            interested.setText("Added!");
            rate.setText("Rate");
            ninterested.setText("I'm not interested");
        }
        /*Button rate = (Button) view.findViewById(R.id.rate_btn);
        Button interested = (Button) view.findViewById(R.id.interested_btn);
        Button not_interested = (Button) view.findViewById(R.id.not_interested_btn);

        rate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rate_icon, 0, 0, 0);
        rate.setBackgroundColor(0);
        rate.setText("Rate");

        interested.setCompoundDrawablesWithIntrinsicBounds(R.drawable.interested_icon, 0, 0, 0);
        interested.setText("I'm interested");

        not_interested.setCompoundDrawablesWithIntrinsicBounds(R.drawable.not_interested_icon, 0, 0, 0);
        not_interested.setText("I'm not interested");*/

        try{
            Bitmap resized_img = Bitmap.createScaledBitmap(selected.getPoster(), 241, 360, true);
            poster.setImageBitmap(resized_img);
        } catch (Exception e){
            e.printStackTrace();
            Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.no_poster);
            Bitmap resized_img = Bitmap.createScaledBitmap(bMap, 241, 360, true);
            BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
            poster.setImageDrawable(img);
        }

        movie_name.setText(selected.getName());
        //movie_name.setTextSize(18);
        movie_release.setText(selected.getRelease());


        String genre_list = selected.getGenre_list();
        genre_list = genre_list.replace("[", "");
        genre_list = genre_list.replace("]", "");
        List<String> str = Arrays.asList(genre_list.split("\\s*,\\s*"));
        for (int i = 0; i < str.size(); i++){
            try {
                movie_genre.append(model.genres.get(str.get(i)));
                if (i == str.size() - 1)
                    movie_genre.append(".");
                else
                    movie_genre.append(", ");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        movie_description.setText(selected.getSinopsis());
    }

    public Context getContext() {
        return view.getContext();
    }



    @Override
    public void update(Observable observable, Object data) {
        if ((Integer) data == 0){
            interested.setText("Added!");
            rate.setText("Rate");
            ninterested.setText("I'm not interested");
        }
        else if ((Integer) data == 1){
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setIsIndicator(false);
            ratingBarDisplay.setNumStars(model.getSelected().getMyVote());
            ratingBarDisplay.setIsIndicator(true);
            interested.setText("I'm interested");
            rate.setText("Rated!");
            ninterested.setText("I'm not interested");
        }
    }
}
