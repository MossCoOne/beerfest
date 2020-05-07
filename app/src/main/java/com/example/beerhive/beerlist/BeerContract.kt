package com.example.beerhive.beerlist

import com.example.beerhive.network.model.BeerResponse

interface BeerContract {
    interface BeerView {
        fun displayBeerList(responseList: List<BeerResponse?>?)
        fun showProgressDialog()
        fun showErrorMessage()
        fun dismissProgressDialog()
    }

    interface UserActionsListener {
        fun loadBeerList()
    }
}