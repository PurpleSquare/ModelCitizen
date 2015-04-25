package com.ps.citizen3.data.api;

import com.ps.citizen3.data.api.GoogleCivicModel.RepResult;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface GoogleCivicService {
    @GET("/civicinfo/v2/representatives?key=AIzaSyC90N11lnxedW92OyJXKPrrMrT6HKxEqaE")
    Observable<RepResult> getReps(
            @Query("address") String address
    );
}
