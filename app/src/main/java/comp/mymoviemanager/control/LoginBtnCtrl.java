package comp.mymoviemanager.control;

import android.content.Intent;
import android.view.View;

import comp.mymoviemanager.MainActivity;
import comp.mymoviemanager.MoviePreferencesActivity;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.LoginView;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class LoginBtnCtrl implements View.OnClickListener{
    LoginView view;
    ApplicationModel model;

    public LoginBtnCtrl(LoginView view, ApplicationModel model){
        this.view = view;
        this.model = model;

        view.data_login.setOnClickListener(this);
        view.fb_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(view.getContext(), MoviePreferencesActivity.class);
        view.getContext().startActivity(i);
    }
}
