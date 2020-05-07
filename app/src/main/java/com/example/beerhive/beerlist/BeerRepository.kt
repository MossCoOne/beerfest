package com.example.beerhive.beerlist

import com.example.beerhive.network.model.BeerResponse

interface BeerRepository {
    fun loadBeerListFromNetwork(beerLoaderCallback: BeerLoaderCallback?)
    interface BeerLoaderCallback {
        fun onBeerListLoaded(beerResponseList: List<BeerResponse?>?)
        fun onErrorOccurred()
    }
}