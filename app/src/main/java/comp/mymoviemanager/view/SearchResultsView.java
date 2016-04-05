package comp.mymoviemanager.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    public ProgressBar progressBar;

    LinearLayout container, infos, result_container;
    TextView name_date, ratings, description;
    ImageView poster;

    public SearchResultsView(View view, MovieModel model) {
        this.view = view;
        this.model = model;

        model.addObserver(this);

        result_container = (LinearLayout) view.findViewById(R.id.results_container);
        search = (SearchView) view.findViewById(R.id.search);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void update(Observable observable, Object data) {
        progressBar.setVisibility(View.GONE);
        if ((Integer) data == 4){
            try {
                container.removeAllViews();
                infos.removeAllViews();
                result_container.removeAllViews();
            } catch (Exception e){
                e.printStackTrace();
            }
            results = model.getSearch_results();
            for (int i = 0; i < results.size(); i++){
                //Set poster to ImageView
                poster = new ImageView(view.getContext());
                //poster.setImageBitmap(results.get(i).getPoster());
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(results.get(i).getPoster(), 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    poster.setImageDrawable(img);
                } catch (Exception ex){
                    Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.no_poster);
                    Bitmap resized_img = Bitmap.createScaledBitmap(bMap, 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    poster.setImageDrawable(img);
                }

                //Set texts to TextView's
                name_date = new TextView(view.getContext());
                name_date.setText(results.get(i).getName() + " (" + results.get(i).getRelease() + ")");
                name_date.setTextSize(18);
                name_date.setTextColor(Color.WHITE);
                name_date.setTypeface(null, Typeface.BOLD);
                ratings = new TextView(view.getContext());
                ratings.setText("Ratings: " + results.get(i).getPopularity() + "/10");
                ratings.setTextColor(Color.WHITE);
                description = new TextView(view.getContext());
                description.setText(results.get(i).getSinopsis());
                description.setMaxLines(3);
                description.setEllipsize(TextUtils.TruncateAt.END);
                description.setTextColor(Color.WHITE);

                //Add views to LinearLayout's
                container = new LinearLayout(view.getContext());
                infos = new LinearLayout(view.getContext());
                LinearLayout.LayoutParams params_info = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 360);
                LinearLayout.LayoutParams params_container = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params_info.setMargins(15, 0, 0, 0);
                params_container.setMargins(0, 5, 0, 5);
                container.setLayoutParams(params_container);
                infos.setOrientation(LinearLayout.VERTICAL);
                infos.setLayoutParams(params_info);
                container.addView(poster);
                infos.addView(name_date);
                infos.addView(ratings);
                infos.addView(description);
                container.addView(infos);
                container.setBackgroundColor(Color.GRAY);
                result_container.addView(container);
            }
        }
    }
}
