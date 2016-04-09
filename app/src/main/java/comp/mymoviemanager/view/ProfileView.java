package comp.mymoviemanager.view;

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
    SearchView search;

    public ProfileView(View view, ApplicationModel model){
        this.view = view;
        this.model = model;

        search = (SearchView) view.findViewById(R.id.search);
        changeSearchViewTextColor(search);
        int closeButtonId = view.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButtonImage = (ImageView) search.findViewById(closeButtonId);
        closeButtonImage.setImageResource(R.drawable.close_icon);
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}
