package com.example.beerhive.network

import com.example.beerhive.network.model.BeerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface BeerServiceApi {
    @GET("beers")
    fun getListOfBeersAsync(): Deferred<List<BeerResponse>>
}