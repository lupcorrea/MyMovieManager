package comp.mymoviemanager.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    }

    public Context getContext(){
        return view.getContext();
    }
}
