package com.zoluxiones.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zoluxiones.data.database.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlaces(dataHistoryList: List<Movie>)

    @Query("select * from movie")
    suspend fun getAllPlaces(): MutableList<Movie>

//
//    @Query("delete from places")
//    fun removeAllPlaces()


}