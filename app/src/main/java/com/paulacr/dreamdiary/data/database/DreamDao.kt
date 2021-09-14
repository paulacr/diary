package com.paulacr.dreamdiary.data.database

import androidx.room.*
import com.paulacr.dreamdiary.data.model.Dream

@Dao
interface DreamDao {

    @Query("SELECT * FROM dream")
    suspend fun getAll(): List<Dream>?

    @Query("SELECT * FROM dream where id IN (:id)")
    suspend fun getDream(id: Int): Dream

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDream(dream: Dream)

    @Delete
    suspend fun deleteDream(dream: Dream)

    @Query("DELETE FROM dream where id IN (:dreamId)")
    suspend fun deleteDreamById(dreamId: Long)
}