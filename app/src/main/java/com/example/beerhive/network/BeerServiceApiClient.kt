package com.example.beerhive.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://api.punkapi.com/v2/"

private val gson: Gson = GsonBuilder().create()
private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(baseUrl)
        .build()

object BeerServiceApiClient {
    val beerServiceApi: BeerServiceApi by lazy { retrofit.create(BeerServiceApi::class.java) }
}
