package com.example.beerhive.network

import com.example.beerhive.network.model.BeerResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BEER_API_BASE_URL = "https://api.punkapi.com/v2/"
interface BeerServiceApi {
    @GET("beers")
    fun getListOfBeersAsync(): Deferred<List<BeerResponse>>

    companion object {
        private val gson: Gson = GsonBuilder().create()
        fun create(): BeerServiceApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BEER_API_BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(BeerServiceApi::class.java)
        }
    }
}