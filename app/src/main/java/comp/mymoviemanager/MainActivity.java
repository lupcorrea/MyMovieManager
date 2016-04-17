package comp.mymoviemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import comp.mymoviemanager.control.MainViewBtnCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MainSearchView;

public class MainActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.view_search_main);
        Toast.makeText(getApplicationContext(), "Novo", Toast.LENGTH_LONG).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText("Home");

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        MainSearchView mainView = new MainSearchView(findViewById(R.id.main_search), model);
        MainViewBtnCtrl btnCtrl = new MainViewBtnCtrl(mainView, model);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.view_search_main;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);//must store the new intent unless getIntent() will return the old one
        //processExtraData();
    }

}
