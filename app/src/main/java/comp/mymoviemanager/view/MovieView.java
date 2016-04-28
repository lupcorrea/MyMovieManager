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
import android.widget.Toast;

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
    public Button rate, interested, remove;
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
        remove = (Button) view.findViewById(R.id.remove_btn);
        //Set the button's text
        Movie d = model.existsIn(m, "topList");
        Movie f = model.existsIn(m, "futureList");
        if (d != null) {
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setRating(d.getMyVote());

            interested.setTextColor(Color.parseColor("#EDEDED"));
            interested.setBackgroundColor(Color.parseColor("#000000"));
            interested.setEnabled(true);
            remove.setText("Remove");
            remove.setTextColor(Color.parseColor("#EDEDED"));
            remove.setBackgroundColor(Color.parseColor("#000000"));
            remove.setEnabled(true);

            rate.setText("Rated!");
            rate.setTextColor(Color.parseColor("#000000"));
            rate.setBackgroundColor(Color.parseColor("#EDEDED"));
            rate.setEnabled(false);
        } else if(f != null){
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setRating(f.getMyVote());

            interested.setText("Added!");
            interested.setTextColor(Color.parseColor("#000000"));
            interested.setBackgroundColor(Color.parseColor("#EDEDED"));
            interested.setEnabled(false);

            rate.setText("Rate");
            rate.setTextColor(Color.parseColor("#EDEDED"));
            rate.setBackgroundColor(Color.parseColor("#000000"));
            rate.setEnabled(true);

            remove.setText("Remove");
            remove.setTextColor(Color.parseColor("#EDEDED"));
            remove.setBackgroundColor(Color.parseColor("#000000"));
            remove.setEnabled(true);
        }

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
        if ((Integer) data == 0) {
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setRating(model.getSelected().getMyVote());
            interested.setText("Added!");
            interested.setTextColor(Color.parseColor("#000000"));
            interested.setBackgroundColor(Color.parseColor("#EDEDED"));
            interested.setEnabled(false);

            rate.setText("Rate");
            rate.setTextColor(Color.parseColor("#EDEDED"));
            rate.setBackgroundColor(Color.parseColor("#000000"));
            rate.setEnabled(true);
            remove.setText("Remove");
            remove.setTextColor(Color.parseColor("#EDEDED"));
            remove.setBackgroundColor(Color.parseColor("#000000"));
            remove.setEnabled(true);

        }
        else if ((Integer) data == 1){
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setRating(model.getSelected().getMyVote());

            interested.setText("I'm interested");
            interested.setTextColor(Color.parseColor("#EDEDED"));
            interested.setBackgroundColor(Color.parseColor("#000000"));
            interested.setEnabled(true);

            rate.setText("Rated!");
            rate.setTextColor(Color.parseColor("#000000"));
            rate.setBackgroundColor(Color.parseColor("#EDEDED"));
            rate.setEnabled(false);

            remove.setText("Remove");
            remove.setTextColor(Color.parseColor("#EDEDED"));
            remove.setBackgroundColor(Color.parseColor("#000000"));
            remove.setEnabled(true);
        }
        else if ((Integer) data == 3) {
            ratingBarDisplayTitle.setText("Your rating for this movie:");
            ratingBarDisplay.setRating(0);

            interested.setText("I'm interested");
            interested.setTextColor(Color.parseColor("#EDEDED"));
            interested.setBackgroundColor(Color.parseColor("#000000"));
            interested.setEnabled(true);

            rate.setText("Rate");
            rate.setTextColor(Color.parseColor("#EDEDED"));
            rate.setBackgroundColor(Color.parseColor("#000000"));
            rate.setEnabled(true);

            remove.setText("Not Added");
            remove.setTextColor(Color.parseColor("#000000"));
            remove.setBackgroundColor(Color.parseColor("#EDEDED"));
            remove.setEnabled(false);
        }
    }
}
