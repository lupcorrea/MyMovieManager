package comp.mymoviemanager.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.Movie;
import comp.mymoviemanager.model.MovieModel;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class SearchView {
    View view;
    MovieModel model;
    public Button go;
    public EditText search;

    public SearchView(View view, final MovieModel model){
        this.model = model;
        this.view = view;

        search = (EditText) view.findViewById(R.id.search);
        go = (Button) view.findViewById(R.id.goButton);
    }
}
