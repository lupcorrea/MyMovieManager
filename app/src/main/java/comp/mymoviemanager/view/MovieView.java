package comp.mymoviemanager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.ApplicationModel;

/**
 * Created by Goldenberg on 06/04/16.
 */
public class MovieView {
    ApplicationModel model;
    View view;
    Movie selected = null;
    public SearchView search;


    public MovieView(View view, ApplicationModel model){
        this.model = model;
        this.view = view;
        this.selected = model.getSelected();

        ImageView poster = (ImageView) view.findViewById(R.id.movie_thumbnail);
        TextView movie_name = (TextView) view.findViewById(R.id.movie_title);
        TextView movie_release = (TextView) view.findViewById(R.id.movie_year);
        TextView movie_genre = (TextView) view.findViewById(R.id.movie_tag);
        TextView movie_description = (TextView) view.findViewById(R.id.movie_plot);
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

        Bitmap resized_img = Bitmap.createScaledBitmap(selected.getPoster(), 241, 360, true);
        poster.setImageBitmap(resized_img);

        movie_name.setText(selected.getName());
        //movie_name.setTextSize(18);
        movie_release.setText(selected.getRelease());

        search = (SearchView) view.findViewById(R.id.search);
        changeSearchViewTextColor(search);
        int closeButtonId = view.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButtonImage = (ImageView) search.findViewById(closeButtonId);
        closeButtonImage.setImageResource(R.drawable.close_icon);

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
    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    public Context getContext() {
        return view.getContext();
    }


}
