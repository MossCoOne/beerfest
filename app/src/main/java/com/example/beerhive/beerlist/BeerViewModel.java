package com.example.beerhive.beerlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.beerhive.network.model.BeerResponse;

import java.util.List;

public class BeerViewModel extends AndroidViewModel {

    private BeerRepository beerRepository;

    private MutableLiveData<List<BeerResponse>> beerListLiveData;

    public BeerViewModel(@NonNull Application application) {
        super(application);
        beerRepository =  new BeerRepositoryImplentation();
        retrieveBeerList();
    }

    public MutableLiveData<List<BeerResponse>> getBeerListLiveData() {
        return beerListLiveData;
    }

    private void retrieveBeerList(){
        beerRepository.loadBeerListFromNetwork(new BeerRepository.BeerLoaderCallback() {
            @Override
            public void onBeerListLoaded(List<BeerResponse> newsArticle) {
                beerListLiveData.setValue(newsArticle);
            }

            @Override
            public void onErrorOccurred() {
                beerListLiveData.setValue(null);
            }
        });
    }
}
