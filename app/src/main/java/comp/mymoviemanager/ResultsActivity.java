package comp.mymoviemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import comp.mymoviemanager.control.NewSearchCtrl;
import comp.mymoviemanager.control.SearchFindBtnCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.SearchResultsView;

/**
 * Created by Goldenberg on 05/04/16.
 */
public class ResultsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search_result);

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        SearchResultsView mainView = new SearchResultsView(findViewById(R.id.results_view), model);
        NewSearchCtrl ctrl = new NewSearchCtrl(model, mainView);
        SearchFindBtnCtrl btnCtrl = new SearchFindBtnCtrl(model, mainView);

        ImageView home = (ImageView) findViewById(R.id.home_action);
        ImageView profile = (ImageView) findViewById(R.id.profile_action);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultsActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }
}
