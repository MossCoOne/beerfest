package com.example.beerhive.network

import com.example.beerhive.network.model.BeerResponse
import retrofit2.Call
import retrofit2.http.GET

interface BeerServiceApi {
    @GET("beers")
    fun listOfBeers(): Call<List<BeerResponse?>?>?
}