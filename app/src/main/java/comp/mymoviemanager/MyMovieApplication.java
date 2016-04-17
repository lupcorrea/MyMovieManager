package comp.mymoviemanager;

import android.app.Application;

import comp.mymoviemanager.model.ApplicationModel;
import comp.mymoviemanager.util.DatabaseManager;

public class MyMovieApplication extends Application {
    private ApplicationModel model = new ApplicationModel();

    public ApplicationModel getModel() { return model; }

    public void setModel(ApplicationModel model) {
        this.model = model;
    }
}
