package comp.mymoviemanager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import comp.mymoviemanager.control.LoginBtnCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.LoginView;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_landing);

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        LoginView view = new LoginView(findViewById(R.id.login_view), model);
        LoginBtnCtrl ctrl = new LoginBtnCtrl(view, model);
    }
}
