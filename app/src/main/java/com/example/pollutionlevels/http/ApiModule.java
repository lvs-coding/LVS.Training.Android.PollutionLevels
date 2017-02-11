package com.example.pollutionlevels.http;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private String baseUrl;

    public ApiModule(String baseUrl) {
        if(baseUrl.trim() == "") {
            throw new IllegalArgumentException("API URL not valid");
        }
        this.baseUrl = baseUrl;
    }

    // Logging
    @Provides
    public OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        /**
         * The BODY logging level logs request and response ines and their respective headers and
         * bodies (if present)
         */
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    //Retrofit
    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Gets an instance of our Retrofit object calling the above methods then, using this Retrofit
     * object it creates an instance of AqicnApi interface by calling the create() method.
     * @return
     */
    @Provides
    public AqicnApi provideApiService() {
        return provideRetrofit(baseUrl, provideClient()).create(AqicnApi.class);
    }
}
