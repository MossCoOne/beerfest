package com.example.beerhive.beerlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.beerhive.database.BeerDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BeerViewModel(private val beerDatabase: BeerDatabase, application: Application) : AndroidViewModel(application) {

    private val beerRepository = BeerRepo(beerDatabase)
    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        viewModelScope.launch {
            beerRepository.refreshBeerList()
        }
    }

    val beerList = beerRepository.beerList

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}