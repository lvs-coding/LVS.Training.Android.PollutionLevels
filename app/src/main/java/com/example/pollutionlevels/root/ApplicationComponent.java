package com.example.pollutionlevels.root;

import com.example.pollutionlevels.MainActivity;
import com.example.pollutionlevels.http.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    void inject(MainActivity target);
}