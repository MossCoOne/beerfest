package com.example.beerhive.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeerServiceApiClient {
    private static final String baseUrl = "https://api.punkapi.com/v2/beers";


    public static BeerServiceApi beerServiceApi;

    private BeerServiceApiClient() {

    }

    public static BeerServiceApi getInstance() {
        Retrofit retrofit;
        if (beerServiceApi == null) {
            Gson gson = new GsonBuilder()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            beerServiceApi = retrofit.create(BeerServiceApi.class);
        }
        return beerServiceApi;
    }

}
