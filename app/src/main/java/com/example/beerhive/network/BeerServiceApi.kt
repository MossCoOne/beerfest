package com.example.beerhive.network

import com.example.beerhive.network.model.BeerResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BEER_API_BASE_URL = "https://api.punkapi.com/v2/"

interface BeerServiceApi {
    @GET("beers")
    suspend fun getListOfBeers(): Response<List<BeerResponse>>

    companion object {
        private val gson: Gson = GsonBuilder().create()
        fun create(): BeerServiceApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BEER_API_BASE_URL)
                .build()
                .create(BeerServiceApi::class.java)
        }
    }
}