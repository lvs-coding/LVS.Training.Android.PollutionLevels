package com.example.pollutionlevels.root;

import android.app.Application;
import android.util.Log;

import com.example.pollutionlevels.R;
import com.example.pollutionlevels.http.ApiModule;

/**
 * Created by laurent on 2/8/17.
 */

public class App extends Application {
    final String LOG_TAG = App.class.getSimpleName();

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        final String API_BASE_URL = this.getResources().getString(R.string.base_url);

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(API_BASE_URL))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}

