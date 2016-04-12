package comp.mymoviemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import comp.mymoviemanager.control.MainViewBtnCtrl;
import comp.mymoviemanager.control.SearchFindCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MainSearchView;

public class MainActivity extends ProfileActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search_main);

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        MainSearchView mainView = new MainSearchView(findViewById(R.id.main_search), model);
        //SearchFindCtrl ctrl = new SearchFindCtrl(model, mainView);
        MainViewBtnCtrl btnCtrl = new MainViewBtnCtrl(mainView, model);


        /*ImageView profile = (ImageView) findViewById(R.id.profile_action);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });*/
    }

}
