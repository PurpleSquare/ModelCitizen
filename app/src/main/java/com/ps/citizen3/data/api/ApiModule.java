package com.ps.citizen3.data.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module
public final class ApiModule {
    public static final String GOOGLE_CIVIC_API_URL = "https://www.googleapis.com";

    @Provides @Singleton
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(GOOGLE_CIVIC_API_URL)
                .build();
    }

    @Provides @Singleton
    GoogleCivicService provideGoogleCivicService(RestAdapter restAdapter) {
        return restAdapter.create(GoogleCivicService.class);
    }
}
