package com.ps.citizen3;

import android.app.Application;

import com.ps.citizen3.ui.RepFragment;

import javax.inject.Singleton;

import dagger.Component;
import timber.log.Timber;

public class App extends Application {
    private AppComponent appComponent;

    @Singleton
    @Component(modules = AppModule.class)
    public interface AppComponent {
        void inject(RepFragment repFragment);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        if (appComponent == null) {
            appComponent = DaggerApp_AppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
