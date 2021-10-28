package com.example.week6_sec4_headsuproom.models

import androidx.room.*


@Dao
interface CelebrityDao {
    @Query("SELECT * FROM Celebrities /* ORDER BY Note DESC*/")
    fun getAllCelebrities():List<Celebrity>

    @Insert
    fun insertCelebrity(input: Celebrity)
    @Delete
    fun deleteCelebrity(id: Celebrity)

    @Update
    fun updateCelebrity(id: Celebrity)
}