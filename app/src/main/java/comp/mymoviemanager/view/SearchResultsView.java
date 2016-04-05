package comp.mymoviemanager.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.MovieModel;

/**
 * Created by Goldenberg on 05/04/16.
 */
public class SearchResultsView implements Observer{
    View view;
    MovieModel model;
    LinkedList<Movie> results;
    public SearchView search;

    LinearLayout container, infos, result_container;
    TextView name_date, ratings, description;
    ImageView poster;

    public SearchResultsView(View view, MovieModel model) {
        this.view = view;
        this.model = model;

        model.addObserver(this);

        result_container = (LinearLayout) view.findViewById(R.id.results_container);
        search = (SearchView) view.findViewById(R.id.search);
    }

    @Override
    public void update(Observable observable, Object data) {
        System.out.println("OLHA EU AQUI");
        if ((Integer) data == 4){
            results = model.getSearch_results();
            for (int i = 0; i < results.size(); i++){
                //Set poster to ImageView
                poster = new ImageView(view.getContext());
                poster.setImageBitmap(results.get(i).getPoster());
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(results.get(i).getPoster(), 482, 720, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    poster.setImageDrawable(img);
                } catch (Exception ex){
                    Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.no_poster);
                    Bitmap resized_img = Bitmap.createScaledBitmap(bMap, 482, 720, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    poster.setImageDrawable(img);
                }

                //Set texts to TextView's
                name_date = new TextView(view.getContext());
                name_date.setText(results.get(i).getName() + " (" + results.get(i).getRelease() + ")");
                ratings = new TextView(view.getContext());
                ratings.setText(results.get(i).getPopularity() + "/10");
                description = new TextView(view.getContext());
                description.setText(results.get(i).getSinopsis());

                //Add views to LinearLayout's
                container.addView(poster);
                infos.addView(name_date);
                infos.addView(ratings);
                infos.addView(description);
                container.addView(infos);
                result_container.addView(container);
            }
        }
    }
}
