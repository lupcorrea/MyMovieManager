package comp.mymoviemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

/**
 * Created by Goldenberg on 11/04/16.
 */
public abstract class ToolBarActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();
    }

    protected abstract int getLayoutResource();

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        //SearchView search = (SearchView) item.getActionView();
        //changeSearchViewTextColor(search);
        //SearchManager searchManager = (SearchManager) ProfileActivity.this.getSystemService(Context.SEARCH_SERVICE);
        //search.setSearchableInfo(searchManager.getSearchableInfo(ProfileActivity.this.getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.home_action:
            {
                /*Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);*/
                System.out.println("Go Home");
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
}
