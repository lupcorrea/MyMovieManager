package comp.mymoviemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.ProfileView;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class ProfileActivity extends AppCompatActivity{
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile_main);

        /*
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home_action:
                        Toast.makeText(ProfileActivity.this, "Share", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }
        });*/

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        ProfileView view = new ProfileView(findViewById(R.id.profile_view), model);
        //SearchFindCtrl ctrl1 = new SearchFindCtrl(model, view);

        //DELETAR ESSAS LINHAS E POR EM OUTRO LUGAR
        ImageView home = (ImageView) findViewById(R.id.home_action);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }*/
}
