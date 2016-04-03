package comp.mymoviemanager.control;

import android.view.View;
import android.view.View.OnClickListener;

import comp.mymoviemanager.model.MovieModel;
import comp.mymoviemanager.view.SearchView;

/**
 * Created by Goldenberg on 02/04/16.
 */
public class SearchButtonCtrl implements OnClickListener{
    MovieModel model;
    SearchView view;

    public SearchButtonCtrl(MovieModel model, SearchView view){
        this.model = model;
        this.view = view;

        view.go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == view.go){
            String text = view.search.getText().toString();
            model.getSuggestions(text);
        }
    }
}
