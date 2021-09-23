package com.paulacr.datamodule.database

import androidx.room.*
import androidx.room.Dao
import com.paulacr.datamodule.model.DreamEntity

@Dao
interface DreamDao {

    @Query("SELECT * FROM dream")
    suspend fun getAll(): List<DreamEntity>?

    @Query("SELECT * FROM dream where id IN (:id)")
    suspend fun getDream(id: Int): DreamEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDream(dream: DreamEntity)

    @Delete
    suspend fun deleteDream(dream: DreamEntity)

    @Query("DELETE FROM dream where id IN (:dreamId)")
    suspend fun deleteDreamById(dreamId: Long)
}