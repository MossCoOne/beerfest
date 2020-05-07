package com.example.beerhive.beerlist

import android.util.Log
import com.example.beerhive.beerlist.BeerRepository.BeerLoaderCallback
import com.example.beerhive.network.BeerServiceApi
import com.example.beerhive.network.BeerServiceApiClient
import com.example.beerhive.network.model.BeerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerRepositoryImplementation : BeerRepository {

    private val beerServiceApi: BeerServiceApi? = BeerServiceApiClient.beerServiceApi

    override fun loadBeerListFromNetwork(beerLoaderCallback: BeerLoaderCallback?) {
        beerServiceApi?.listOfBeers()?.enqueue(object : Callback<List<BeerResponse?>?> {
            override fun onResponse(call: Call<List<BeerResponse?>?>, response: Response<List<BeerResponse?>?>) {
                if (response.isSuccessful && response.body() != null) {
                    beerLoaderCallback?.onBeerListLoaded(response.body())
                } else {
                    beerLoaderCallback!!.onErrorOccurred()
                }
            }

            override fun onFailure(call: Call<List<BeerResponse?>?>, t: Throwable) {
                Log.d("Call Failure", t.message)
                beerLoaderCallback?.onErrorOccurred()
            }
        })
    }

}