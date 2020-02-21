package com.example.beerhive.network;

import com.example.beerhive.network.model.BeerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerServiceApi {

    @GET("beers")
    Call<List<BeerResponse>> getListOfBeers();
}
