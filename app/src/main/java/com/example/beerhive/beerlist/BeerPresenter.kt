package com.example.beerhive.beerlist

import com.example.beerhive.beerlist.BeerContract.BeerView
import com.example.beerhive.beerlist.BeerContract.UserActionsListener
import com.example.beerhive.beerlist.BeerRepository.BeerLoaderCallback
import com.example.beerhive.network.model.BeerResponse

class BeerPresenter(private val view: BeerView) : UserActionsListener {
    private val beerRepository: BeerRepository
    override fun loadBeerList() {
        view.showProgressDialog()
        beerRepository.loadBeerListFromNetwork(object : BeerLoaderCallback {
            override fun onBeerListLoaded(beerResponseList: MutableList<BeerResponse?>?) {
                view.displayBeerList(beerResponseList)
                view.dismissProgressDialog()
            }

            override fun onErrorOccurred() {
                view.dismissProgressDialog()
                view.showErrorMessage()
            }
        })
    }

    init {
        beerRepository = BeerRepositoryImplementation()
    }
}