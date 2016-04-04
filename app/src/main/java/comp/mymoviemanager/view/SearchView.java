package comp.mymoviemanager.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.MovieModel;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class SearchView implements Observer{
    View view;
    MovieModel model;
    public Button go;
    ImageButton movieButton;
    public EditText search;
    LinkedList<Button> suggestion_buttonList = new LinkedList<>();
    LinkedList<Button> new_buttonList = new LinkedList<>();
    LinkedList<Button> friends_buttonList = new LinkedList<>();
    LinkedList<Movie> suggestion_result;

    LinearLayout suggestions;
    LinearLayout newTheatre;
    LinearLayout friends;

    ImageView teste;

    public SearchView(View view, final MovieModel model){
        this.model = model;
        this.view = view;

        model.addObserver(this);

        search = (EditText) view.findViewById(R.id.search);
        go = (Button) view.findViewById(R.id.goButton);

        suggestions = (LinearLayout) view.findViewById(R.id.suggestionView);
        newTheatre = (LinearLayout) view.findViewById(R.id.newTheatresView);
        friends = (LinearLayout) view.findViewById(R.id.friendsView);

        teste = (ImageView) view.findViewById(R.id.test);

        model.getSuggestions();

    }

    @Override
    public void update(Observable observable, Object data) {
        //UPDATE SUGGESTIONS
        if ((Integer) data == 0){
            System.out.println("oi");
            suggestion_result = model.getSuggestionsResult();
            for (int i = 0; i < suggestion_result.size(); i++){
                movieButton = new ImageButton(view.getContext());
                //movieButton.setText(suggestion_result.get(i).getName());
                //movieButton.setTextColor(-1);
                //movieButton.setTextSize(10);
                Bitmap resized_img = Bitmap.createScaledBitmap(suggestion_result.get(i).getPoster(), 241, 360, true);
                BitmapDrawable img = new BitmapDrawable(view.getResources(),resized_img);
                movieButton.setBackground(img);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 15, 0);
                movieButton.setLayoutParams(params);
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //movieButton.setLayoutParams(new LinearLayout.LayoutParams(241,360));
                //int resId = suggestion_result.get(i).getPoster().getGenerationId();
                //System.out.println(suggestion_result.get(i).getPoster());
                //teste.setImageBitmap(suggestion_result.get(0).getPoster());
                //teste.setImageDrawable(img);
                //movieButton.setCompoundDrawablesWithIntrinsicBounds(0, img, 0, 0);
                //movieButton.setBackgroundColor(0);
                suggestions.addView(movieButton);
            }
        }
    }
}
