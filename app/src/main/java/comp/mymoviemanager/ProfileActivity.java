package comp.mymoviemanager;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import comp.mymoviemanager.view.ProfileView;

/**
 * Created by Goldenberg on 09/04/16.
 */
public class ProfileActivity extends ToolBarActivity{
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile_main);


        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText("Profile");
        */

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        ProfileView view = new ProfileView(findViewById(R.id.profile_view), model);
        //ProfileSearchCtrl ctrl1 = new ProfileSearchCtrl(model, view);

        //DELETAR ESSAS LINHAS E POR EM OUTRO LUGAR
        /*ImageView home = (ImageView) findViewById(R.id.home_action);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });*/
    }


    @Override
    public int getLayoutResource() {
        return R.layout.view_profile_main;
    }




    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView search = (SearchView) item.getActionView();
        changeSearchViewTextColor(search);
        //SearchManager searchManager = (SearchManager) ProfileActivity.this.getSystemService(Context.SEARCH_SERVICE);
        //search.setSearchableInfo(searchManager.getSearchableInfo(ProfileActivity.this.getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.home_action:
            {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
            case R.id.profile_action:
            {}
            case R.id.favorite_action:
            {}
            case R.id.action_search:
            {}
        }
        return super.onOptionsItemSelected(item);
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
    */
}
