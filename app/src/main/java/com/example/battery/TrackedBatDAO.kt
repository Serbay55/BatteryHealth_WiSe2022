package com.example.battery

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TrackedBatDAO {

    @Query("SELECT * FROM tracked_charges")
    fun getAll(): List<Tracked>

    @Query("SELECT * FROM tracked_charges WHERE date_of_occurrence LIKE :roll LIMIT 1")
    suspend fun findByRoll(roll: Int): Tracked

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tracked: Tracked)

    @Delete
    suspend fun delete(tracked: Tracked)

    @Query("DELETE FROM tracked_charges")
    suspend fun deleteAll()

}