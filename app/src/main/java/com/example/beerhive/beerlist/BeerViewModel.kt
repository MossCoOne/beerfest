package com.example.beerhive.beerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(beerRepository: IBeerRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            beerRepository.refreshBeerList()
        }
    }

    val beerList = beerRepository.getBeerList()
}