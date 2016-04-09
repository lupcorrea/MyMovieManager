package comp.mymoviemanager.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.ApplicationModel;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class MoviePreferencesView {
    View view;
    ApplicationModel model;

    public Button skip;

    public MoviePreferencesView(View view, ApplicationModel model){
        this.view = view;
        this.model = model;

        skip = (Button) view.findViewById(R.id.skip);
    }

    public Context getContext(){
        return view.getContext();
    }
}
