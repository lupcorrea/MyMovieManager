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
import android.widget.SearchView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.model.Movie;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class ProfileView implements Observer{
    View view;
    ApplicationModel model;
    public SearchView search;
    LinkedList<Movie> top = new LinkedList();
    LinkedList<Movie> future = new LinkedList();
    LinearLayout myFavs, myFavsText, myFuture, myFutureText;
    ImageButton movieButton;
    TextView movie_name;

    public ProfileView(View view, ApplicationModel model){
        this.view = view;
        this.model = model;

        model.addObserver(this);

        myFavs = (LinearLayout) view.findViewById(R.id.scroll_profile_top_movies);
        myFavsText = (LinearLayout) view.findViewById(R.id.scroll_profile_top_moviesText);
        myFuture = (LinearLayout) view.findViewById(R.id.scroll_profile_todo_movies);
        myFutureText = (LinearLayout) view.findViewById(R.id.scroll_profile_todo_moviesText);

        model.getTopList();
        //model.getFutureList();

    }

    public Context getContext(){
        return view.getContext();
    }

    @Override
    public void update(Observable observable, Object data) {
        if ((Integer) data == 5){
            System.out.println("TO aqui");
            top = model.top;
            for (int i = 0; i < top.size(); i++){
                movieButton = new ImageButton(view.getContext());
                movie_name = new TextView(view.getContext());
                movie_name.setText(top.get(i).getName());
                movie_name.setTextColor(-1);
                movie_name.setTextSize(10);
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(top.get(i).getPoster(), 241, 360, true);
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
                movieButton.setId(top.get(i).getId());
                movie_name.setLayoutParams(params1);

                myFavs.addView(movieButton);
                myFavsText.addView(movie_name);
            }
        }
        else if ((Integer) data == 6){
            future = model.future;
            for (int i = 0; i < future.size(); i++){
                movieButton = new ImageButton(view.getContext());
                movie_name = new TextView(view.getContext());
                movie_name.setText(future.get(i).getName());
                movie_name.setTextColor(-1);
                movie_name.setTextSize(10);
                try{
                    Bitmap resized_img = Bitmap.createScaledBitmap(future.get(i).getPoster(), 241, 360, true);
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
                movieButton.setId(future.get(i).getId());
                movie_name.setLayoutParams(params1);

                myFuture.addView(movieButton);
                myFutureText.addView(movie_name);
            }
        }
    }
}
