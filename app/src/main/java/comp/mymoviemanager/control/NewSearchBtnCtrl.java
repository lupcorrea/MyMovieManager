package comp.mymoviemanager.control;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import comp.mymoviemanager.MovieActivity;
import comp.mymoviemanager.model.MovieModel;
import comp.mymoviemanager.view.MainSearchView;

/**
 * Created by Goldenberg on 06/04/16.
 */
public class NewSearchBtnCtrl implements ViewGroup.OnHierarchyChangeListener {
    MovieModel model;
    MainSearchView view;

    public NewSearchBtnCtrl (MainSearchView view, MovieModel model){
        this.model = model;
        this.view = view;

        view.suggestions.setOnHierarchyChangeListener(this);
        view.upcoming.setOnHierarchyChangeListener(this);
        view.popular.setOnHierarchyChangeListener(this);
        view.topRated.setOnHierarchyChangeListener(this);

    }


    @Override
    public void onChildViewAdded(View parent, View child) {
        if (child instanceof ImageButton){
            ImageButton temp;
            temp = (ImageButton) child;
            temp.setOnClickListener(handler);
            //System.out.println("New Button Created");
        }
    }

    @Override
    public void onChildViewRemoved(View parent, View child) {

    }

    public View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            model.setMovieFromId(v.getId());
            Intent i = new Intent(view.getContext(), MovieActivity.class);
            view.getContext().startActivity(i);
        }
    };

}
