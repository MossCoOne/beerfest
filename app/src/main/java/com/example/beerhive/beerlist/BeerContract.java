package com.example.beerhive.beerlist;

import com.example.beerhive.network.model.BeerResponse;

import java.util.List;

public interface BeerContract {
    interface BeerView {
        void displayBeerList(List<BeerResponse> responseList);

        void showProgressDialog();

        void showErrorMesage();

        void dismissProgressDialog();
    }

    interface UserActionsListener {
        void loadBeerList();
    }
}
