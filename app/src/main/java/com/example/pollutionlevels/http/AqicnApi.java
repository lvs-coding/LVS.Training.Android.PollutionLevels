package com.example.pollutionlevels.http;

import com.example.pollutionlevels.http.apimodel.Aqicn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by laurent on 2/8/17.
 */

public interface AqicnApi {
    @GET("feed/here/")
    Call<Aqicn> getHerePollution(@Query("token") String token); // Query token parameter needed for API auth

    @GET("feed/here/")
    Observable<Aqicn> getHerePollutionObservable(@Query("token") String token); // Query token parameter needed for API auth
}
