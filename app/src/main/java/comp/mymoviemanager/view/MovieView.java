package comp.mymoviemanager.view;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

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

        String genre_list = selected.getGenre_list();
        genre_list = genre_list.replace("[", "");
        genre_list = genre_list.replace("]", "");
        List<String> str = Arrays.asList(genre_list.split("\\s*,\\s*"));
        for (int i = 0; i < str.size(); i++){
            if (str.get(i).equals("28")){
                movie_genre.append("Action ");
            }
            else if (str.get(i).equals("12")){
                movie_genre.append("Adventure ");
            }
            else if (str.get(i).equals("16")){
                movie_genre.append("Animation ");
            }
            else if (str.get(i).equals("35")){
                movie_genre.append("Comedy ");
            }
            else if (str.get(i).equals("80")){
                movie_genre.append("Crime ");
            }
            else if (str.get(i).equals("99")){
                movie_genre.append("Documentary ");
            }
            else if (str.get(i).equals("18")){
                movie_genre.append("Drama ");
            }
            else if (str.get(i).equals("10751")){
                movie_genre.append("Family ");
            }
            else if (str.get(i).equals("14")){
                movie_genre.append("Fantasy ");
            }
            else if (str.get(i).equals("10769")){
                movie_genre.append("Foreign ");
            }
            else if (str.get(i).equals("36")){
                movie_genre.append("History ");
            }
            else if (str.get(i).equals("27")){
                movie_genre.append("Horror ");
            }
            else if (str.get(i).equals("10402")){
                movie_genre.append("Music ");
            }
            else if (str.get(i).equals("9648")){
                movie_genre.append("Mystery ");
            }
            else if (str.get(i).equals("10749")){
                movie_genre.append("Romance ");
            }
            else if (str.get(i).equals("878")){
                movie_genre.append("Science Fiction ");
            }
            else if (str.get(i).equals("10770")){
                movie_genre.append("TV Movie ");
            }
            else if (str.get(i).equals("53")){
                movie_genre.append("Thriller ");
            }
            else if (str.get(i).equals("10752")){
                movie_genre.append("War ");
            }
            else if (str.get(i).equals("37")){
                movie_genre.append("Western ");
            }
        }
        //movie_genre.setText(selected.getGenre_list());
        movie_description.setText(selected.getSinopsis());
    }
}
