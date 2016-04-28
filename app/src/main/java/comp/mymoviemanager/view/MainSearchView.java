package comp.mymoviemanager.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.ApplicationModel;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class MainSearchView implements Observer{
    View view;
    ApplicationModel model;
    ImageButton movieButton;
    TextView movie_name;
    ProgressBar progressBar, progressBar1, progressBar2, progressBar3;

    LinkedList<Movie> suggestion_result;

   // public LinearLayout suggestions;
    //LinearLayout suggestionsText;
    public LinearLayout upcoming;
    LinearLayout upcomingText;
    public LinearLayout popular;
    LinearLayout popularText;
    public LinearLayout topRated;
    LinearLayout topRatedText;


    public MainSearchView(View view, final ApplicationModel model){
        this.model = model;
        this.view = view;

        model.addObserver(this);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar) view.findViewById(R.id.progressBar3);

        //suggestions = (LinearLayout) view.findViewById(R.id.suggestionView);
        //suggestionsText = (LinearLayout) view.findViewById(R.id.suggestionViewText);
        upcoming = (LinearLayout) view.findViewById(R.id.upcomingView);
        upcomingText = (LinearLayout) view.findViewById(R.id.upcomingViewText);
        popular = (LinearLayout) view.findViewById(R.id.popularView);
        popularText = (LinearLayout) view.findViewById(R.id.popularViewText);
        topRated = (LinearLayout) view.findViewById(R.id.topRatedView);
        topRatedText = (LinearLayout) view.findViewById(R.id.topRatedViewText);

        model.getSuggestions();
//        progressBar.setVisibility(View.VISIBLE);
        progressBar1.setVisibility(View.VISIBLE);
        progressBar2.setVisibility(View.VISIBLE);
        progressBar3.setVisibility(View.VISIBLE);

    }

    @Override
    public void update(Observable observable, Object data) {
        //UPDATE SUGGESTIONS
        /*
        if ((Integer) data == 0){
            progressBar.setVisibility(View.GONE);
            suggestion_result = model.getResult(0);
            for (int i = 0; i < suggestion_result.size(); i++){
                movieButton = new ImageButton(view.getContext());
                movie_name = new TextView(view.getContext());
                movie_name.setText(suggestion_result.get(i).getName());
                movie_name.setTextColor(-1);
                movie_name.setTextSize(10);
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(suggestion_result.get(i).getPoster(), 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                } catch (Exception ex){
                    Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.no_poster);
                    Bitmap resized_img = Bitmap.createScaledBitmap(bMap, 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 15, 0);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(241, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 0, 15, 0);
                movieButton.setLayoutParams(params);
                movieButton.setId(suggestion_result.get(i).getId());
                movie_name.setLayoutParams(params1);

                suggestions.addView(movieButton);
                suggestionsText.addView(movie_name);
            }
        }*/
        if ((Integer) data == 1){
            progressBar1.setVisibility(View.GONE);
            suggestion_result = model.getResult(1);
            for (int i = 0; i < suggestion_result.size(); i++){
                movieButton = new ImageButton(view.getContext());
                movie_name = new TextView(view.getContext());
                movie_name.setText(suggestion_result.get(i).getName());
                movie_name.setTextColor(-1);
                movie_name.setTextSize(10);
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(suggestion_result.get(i).getPoster(), 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                } catch (Exception ex){
                    Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.no_poster);
                    Bitmap resized_img = Bitmap.createScaledBitmap(bMap, 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 15, 0);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(241, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 0, 15, 0);
                movieButton.setLayoutParams(params);
                movieButton.setId(suggestion_result.get(i).getId());
                movie_name.setLayoutParams(params1);
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(241,360));
                //int resId = suggestion_result.get(i).getPoster().getGenerationId();
                //System.out.println(suggestion_result.get(i).getPoster());
                //teste.setImageBitmap(suggestion_result.get(0).getPoster());
                //teste.setImageDrawable(img);
                //movieButton.setCompoundDrawablesWithIntrinsicBounds(0, img, 0, 0);
                //movieButton.setBackgroundColor(0);
                popular.addView(movieButton);
                popularText.addView(movie_name);
            }
        }
        if ((Integer) data == 2){
            progressBar2.setVisibility(View.GONE);
            suggestion_result = model.getResult(2);
            for (int i = 0; i < suggestion_result.size(); i++){
                movieButton = new ImageButton(view.getContext());
                movie_name = new TextView(view.getContext());
                movie_name.setText(suggestion_result.get(i).getName());
                movie_name.setTextColor(-1);
                movie_name.setTextSize(10);
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(suggestion_result.get(i).getPoster(), 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                } catch (Exception ex){
                    Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.no_poster);
                    Bitmap resized_img = Bitmap.createScaledBitmap(bMap, 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 15, 0);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(241, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 0, 15, 0);
                movieButton.setLayoutParams(params);
                movieButton.setId(suggestion_result.get(i).getId());
                movie_name.setLayoutParams(params1);
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(241,360));
                //int resId = suggestion_result.get(i).getPoster().getGenerationId();
                //System.out.println(suggestion_result.get(i).getPoster());
                //teste.setImageBitmap(suggestion_result.get(0).getPoster());
                //teste.setImageDrawable(img);
                //movieButton.setCompoundDrawablesWithIntrinsicBounds(0, img, 0, 0);
                //movieButton.setBackgroundColor(0);
                topRated.addView(movieButton);
                topRatedText.addView(movie_name);
            }
        }
        if ((Integer) data == 3){
            progressBar3.setVisibility(View.GONE);
            suggestion_result = model.getResult(3);
            for (int i = 0; i < suggestion_result.size(); i++){
                movieButton = new ImageButton(view.getContext());
                movie_name = new TextView(view.getContext());
                movie_name.setText(suggestion_result.get(i).getName());
                movie_name.setTextColor(-1);
                movie_name.setTextSize(10);
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(suggestion_result.get(i).getPoster(), 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                } catch (Exception ex){
                    Bitmap bMap = BitmapFactory.decodeResource(view.getResources(), R.drawable.no_poster);
                    Bitmap resized_img = Bitmap.createScaledBitmap(bMap, 241, 360, true);
                    BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                    movieButton.setBackground(img);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 15, 0);
                LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(241, LinearLayout.LayoutParams.WRAP_CONTENT);
                params1.setMargins(0, 0, 15, 0);
                movieButton.setLayoutParams(params);
                movieButton.setId(suggestion_result.get(i).getId());
                movie_name.setLayoutParams(params1);
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(241,360));
                //int resId = suggestion_result.get(i).getPoster().getGenerationId();
                //System.out.println(suggestion_result.get(i).getPoster());
                //teste.setImageBitmap(suggestion_result.get(0).getPoster());
                //teste.setImageDrawable(img);
                //movieButton.setCompoundDrawablesWithIntrinsicBounds(0, img, 0, 0);
                //movieButton.setBackgroundColor(0);
                upcoming.addView(movieButton);
                upcomingText.addView(movie_name);
            }
        }
    }

    public Context getContext() {
        return view.getContext();
    }
}
