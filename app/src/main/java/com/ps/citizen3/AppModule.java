package com.ps.citizen3;

import android.app.Application;

import com.ps.citizen3.data.api.ApiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module( includes = ApiModule.class)
public final class AppModule {
    private final App app;

    public AppModule(App app) {
            this.app = app;
    }

    @Provides @Singleton Application provideApplication () {
            return app;
    }
}
