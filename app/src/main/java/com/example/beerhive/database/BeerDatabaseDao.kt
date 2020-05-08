package com.example.beerhive.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BeerDatabaseDao {

    @Query("SELECT * FROM Beer")
    fun getAllBeers(): List<Beer>

    @Insert
    fun insertAll(vararg: Beer)

    @Delete
    fun removeBeer(beer: Beer)

    @Query("DELETE FROM Beer")
    fun clearAllBeer()
}