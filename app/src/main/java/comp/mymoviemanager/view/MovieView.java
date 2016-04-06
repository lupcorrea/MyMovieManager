package comp.mymoviemanager.view;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.MovieModel;

/**
 * Created by Goldenberg on 06/04/16.
 */
public class MovieView {
    MovieModel model;
    View view;
    Movie selected = null;

    public MovieView(View view, MovieModel model){
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
        movie_genre.setText(selected.getGenre_list());
        movie_description.setText(selected.getSinopsis());
    }
}
