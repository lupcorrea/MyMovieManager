package comp.mymoviemanager.control;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import comp.mymoviemanager.MovieActivity;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.ProfileView;

/**
 * Created by Goldenberg on 24/04/16.
 */
public class ProfileBtnCtrl implements ViewGroup.OnHierarchyChangeListener{
    ApplicationModel model;
    ProfileView view;

    public ProfileBtnCtrl(ApplicationModel model, ProfileView view){
        this.model = model;
        this.view = view;

        view.myFavs.setOnHierarchyChangeListener(this);
        view.myFuture.setOnHierarchyChangeListener(this);

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
