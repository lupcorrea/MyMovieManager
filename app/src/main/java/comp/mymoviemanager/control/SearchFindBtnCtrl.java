package comp.mymoviemanager.control;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import comp.mymoviemanager.MovieActivity;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.SearchResultsView;

/**
 * Created by Goldenberg on 07/04/16.
 */
public class SearchFindBtnCtrl implements ViewGroup.OnHierarchyChangeListener {
    ApplicationModel model;
    SearchResultsView view;

    public SearchFindBtnCtrl (ApplicationModel model, SearchResultsView view){
        this.model = model;
        this.view = view;

        view.result_container.setOnHierarchyChangeListener(this);
    }

    @Override
    public void onChildViewAdded(View parent, View child) {
        if (child instanceof LinearLayout){
            LinearLayout temp;
            temp = (LinearLayout) child;
            temp.setOnClickListener(handler);
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
