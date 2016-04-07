package comp.mymoviemanager.view;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
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

    public MovieView(View view, ApplicationModel model){
        this.model = model;
        this.view = view;
        this.selected = model.getSelected();

        ImageView poster = (ImageView) view.findViewById(R.id.movie_thumbnail);
        TextView movie_name = (TextView) view.findViewById(R.id.movie_title);
        TextView movie_release = (TextView) view.findViewById(R.id.movie_year);
        TextView movie_genre = (TextView) view.findViewById(R.id.movie_tag);
        TextView movie_description = (TextView) view.findViewById(R.id.movie_plot);

        Bitmap resized_img = Bitmap.createScaledBitmap(selected.getPoster(), 241, 360, true);
        poster.setImageBitmap(resized_img);

        movie_name.setText(selected.getName());
        movie_name.setTextSize(18);
        movie_release.setText(selected.getRelease());

        String genre_list = selected.getGenre_list();
        genre_list = genre_list.replace("[", "");
        genre_list = genre_list.replace("]", "");
        List<String> str = Arrays.asList(genre_list.split("\\s*,\\s*"));
        for (int i = 0; i < str.size(); i++){

            movie_genre.append(model.genres.get(str.get(i)));
            if (i == str.size() - 1)
                movie_genre.append(".");
            else
                movie_genre.append(", ");
        }
        movie_description.setText(selected.getSinopsis());
    }
}
