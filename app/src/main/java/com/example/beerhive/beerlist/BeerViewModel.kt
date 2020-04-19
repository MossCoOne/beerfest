package com.example.beerhive.beerlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beerhive.beerdetail.Beer
import com.example.beerhive.network.model.BeerResponse

class BeerViewModel : ViewModel(){
    private val beerListLiveData  : MutableLiveData<List<BeerResponse>> = TODO()
//    private BeerRepository beerRepository;
    //
    //    private MutableLiveData<List<BeerResponse>> beerListLiveData;
    //
    //    public BeerViewModel(@NonNull Application application) {
    //        super(application);
    //        beerRepository =  new BeerRepositoryImplentation();
    //        retrieveBeerList();
    //    }
    //
    //    public MutableLiveData<List<BeerResponse>> getBeerListLiveData() {
    //        return beerListLiveData;
    //    }
    //
    //    private void retrieveBeerList(){
    //        beerRepository.loadBeerListFromNetwork(new BeerRepository.BeerLoaderCallback() {
    //            @Override
    //            public void onBeerListLoaded(List<BeerResponse> newsArticle) {
    //                beerListLiveData.setValue(newsArticle);
    //            }
    //
    //            @Override
    //            public void onErrorOccurred() {
    //                beerListLiveData.setValue(null);
    //            }
    //        });
    //    }

    fun getListOfBeers(): MutableLiveData<List<BeerResponse>> {
        return beerListLiveData
    }

}