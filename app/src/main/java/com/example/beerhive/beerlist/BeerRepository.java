package com.example.beerhive.beerlist;

import androidx.lifecycle.MutableLiveData;

import com.example.beerhive.network.model.BeerResponse;

import java.util.List;

public interface BeerRepository {

    void loadBeerListFromNetwork(BeerLoaderCallback beerLoaderCallback);

    interface BeerLoaderCallback {
        void onBeerListLoaded(List<BeerResponse> beerResponseList);

        void onErrorOccurred();
    }

}
