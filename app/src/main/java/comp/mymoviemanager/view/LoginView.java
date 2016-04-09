package comp.mymoviemanager.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import comp.mymoviemanager.R;
import comp.mymoviemanager.model.ApplicationModel;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class LoginView {
    View view;
    ApplicationModel model;

    public Button fb_login, data_login;

    public LoginView(View view, ApplicationModel model){
        this.view = view;
        this.model = model;

        fb_login = (Button) view.findViewById(R.id.fb_login);
        data_login = (Button) view.findViewById(R.id.data_login);
    }

    public Context getContext(){
        return view.getContext();
    }
}
