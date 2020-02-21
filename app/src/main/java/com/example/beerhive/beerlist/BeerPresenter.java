package com.example.beerhive.beerlist;

import com.example.beerhive.network.model.BeerResponse;

import java.util.List;

public class BeerPresenter implements BeerContract.UserActionsListener {

    private BeerRepository beerRepository;
    private BeerContract.BeerView view;

    public BeerPresenter(BeerContract.BeerView view) {
        this.view = view;
        beerRepository = new BeerRepositoryImplentation();
    }

    @Override
    public void loadBeerList() {
        view.showProgressDialog();

        beerRepository.loadBeerListFromNetwork(new BeerRepository.BeerLoaderCallback() {
            @Override
            public void onBeerListLoaded(List<BeerResponse> beerResponseList) {
                view.displayBeerList(beerResponseList);
                view.dismissProgressDialog();
            }

            @Override
            public void onErrorOccurred() {
                view.dismissProgressDialog();
                view.showErrorMesage();
            }
        });
    }
}
