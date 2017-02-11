package com.example.pollutionlevels;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pollutionlevels.http.AqicnApi;
import com.example.pollutionlevels.http.apimodel.Aqicn;
import com.example.pollutionlevels.http.apimodel.Data;
import com.example.pollutionlevels.http.apimodel.Iaqi;
import com.example.pollutionlevels.root.App;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = MainActivity.class.getSimpleName();

    @Inject
    AqicnApi aqicnApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG,"Start");

        ((App)getApplication()).getComponent().inject(this);
        Call<Aqicn> call = aqicnApi.getHerePollution(getResources().getString(R.string.aqicn_token));
        call.enqueue(new Callback<Aqicn>() {
            @Override
            public void onResponse(Call<Aqicn> call, Response<Aqicn> response) {
                Data data = response.body().getData();
                Log.d("Name : ",data.getCity().getName());

                Iaqi iaqi = data.getIaqi();
                Log.i("PM2.5", "Fine particulate matter - " + iaqi.getPm25().getV().toString());
                Log.i("PM10", "Respirable particulate matter - " + iaqi.getPm10().getV().toString());
                Log.i("O3", "Ozone - " + iaqi.getO3().getV().toString());
                Log.i("NO2", "Nitrogen dioxide - " + iaqi.getO3().getV().toString());
                Log.i("SO2","Sulfur dioxide - " + iaqi.getSo2().getV().toString());
                Log.i("CO","Carbon monoxide - " + iaqi.getCo().getV().toString());
                Log.i("Temp","Temperature - " + iaqi.getT().getV().toString());
                Log.i("Pressure","Pressure - " + iaqi.getP().getV().toString());
                Log.i("Humidity","Humidity - " + iaqi.getH().getV().toString());
                Log.i("Wind","Wind - " + iaqi.getW().getV().toString());

            }

            @Override
            public void onFailure(Call<Aqicn> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
