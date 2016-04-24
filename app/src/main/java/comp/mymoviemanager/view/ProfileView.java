package comp.mymoviemanager.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.ApplicationModel;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class ProfileView {
    View view;
    ApplicationModel model;
    public SearchView search;

    public ProfileView(View view, ApplicationModel model){
        this.view = view;
        this.model = model;

        LinearLayout myFavs = (LinearLayout) view.findViewById(R.id.scroll_profile_top_movies);
        LinearLayout myFuture = (LinearLayout) view.findViewById(R.id.scroll_profile_todo_movies);

    }

    public Context getContext(){
        return view.getContext();
    }
}
