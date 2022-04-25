package com.example.beerhive.beerlist

import com.example.beerhive.database.BeerDatabaseDao
import com.example.beerhive.network.BeerServiceApi
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineDispatcher
import org.mockito.kotlin.mock

class BeerRepositoryTest : TestCase() {


    private val beerDatabaseDaoMock = mock<BeerDatabaseDao>()
    private val beerServiceApiMock = mock<BeerServiceApi>()
    private val ioDispatcher = mock<CoroutineDispatcher>()

    //lateinit var
}