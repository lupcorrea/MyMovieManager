package comp.mymoviemanager;

import android.app.Activity;
import android.os.Bundle;

import comp.mymoviemanager.control.MainViewBtnCtrl;
import comp.mymoviemanager.control.SearchFindCtrl;
import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.view.MainSearchView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search_main);

        ApplicationModel model = ((MyMovieApplication) this.getApplication()).getModel();

        MainSearchView mainView = new MainSearchView(findViewById(R.id.main_search), model);
        SearchFindCtrl ctrl = new SearchFindCtrl(model, mainView);
        MainViewBtnCtrl btnCtrl = new MainViewBtnCtrl(mainView, model);
    }

}
