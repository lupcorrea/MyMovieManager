package comp.mymoviemanager;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;

import comp.mymoviemanager.control.ProfileSearchCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.model.Profile;
import comp.mymoviemanager.util.DatabaseManager;
import comp.mymoviemanager.view.ProfileView;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class ProfileActivity extends ToolBarActivity{
    private Toolbar toolbar;

    ApplicationModel model;
    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.view_profile_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText("Profile");

        model = ((MyMovieApplication) this.getApplication()).getModel();
        db = DatabaseManager.getInstance(getApplicationContext());

        ProfileView view = new ProfileView(findViewById(R.id.profile_view), model);

        Toast.makeText(getApplicationContext(), db.toString(), Toast.LENGTH_LONG).show();
    }


    @Override
    public int getLayoutResource() {
        return R.layout.view_profile_main;
    }

}
