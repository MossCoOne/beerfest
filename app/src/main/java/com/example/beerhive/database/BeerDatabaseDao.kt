package com.example.beerhive.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeerDatabaseDao {

    @Query("SELECT * FROM DataBaseBeer")
    fun getAllBeers(): LiveData<List<DataBaseBeer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dataBaseBeers: DataBaseBeer)

    @Delete
    fun removeBeer(dataBaseBeer: DataBaseBeer)

    @Query("DELETE FROM DataBaseBeer")
    fun clearAllBeer()
}