package com.example.beerhive.beerlist;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.beerhive.network.BeerServiceApi;
import com.example.beerhive.network.BeerServiceApiClient;
import com.example.beerhive.network.model.BeerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerRepositoryImplementation implements BeerRepository {

   private BeerServiceApi beerServiceApi;

    public BeerRepositoryImplementation() {
        this.beerServiceApi = BeerServiceApiClient.getInstance();
    }

    @Override
    public void loadBeerListFromNetwork(final BeerLoaderCallback beerLoaderCallback) {
        beerServiceApi.getListOfBeers().enqueue(new Callback<List<BeerResponse>>() {
            @Override
            public void onResponse(Call<List<BeerResponse>> call, Response<List<BeerResponse>> response) {
                if (response.isSuccessful() && response.body() != null){
                    beerLoaderCallback.onBeerListLoaded(response.body());
                }else {
                    beerLoaderCallback.onErrorOccurred();
                }
            }

            @Override
            public void onFailure(Call<List<BeerResponse>> call, Throwable t) {
                Log.d("Call Failure",t.getMessage());
                beerLoaderCallback.onErrorOccurred();
            }
        });
    }
}
