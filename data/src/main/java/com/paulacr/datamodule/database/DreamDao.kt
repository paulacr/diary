package com.paulacr.datamodule.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paulacr.datamodule.model.DreamEntity

@Dao
interface DreamDao {

    @Query("SELECT * FROM dreamentity")
    suspend fun getAll(): List<DreamEntity>?

    @Query("SELECT * FROM dreamentity where id IN (:id)")
    suspend fun getDream(id: Int): DreamEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDream(dream: DreamEntity)

    @Delete
    suspend fun deleteDream(dream: DreamEntity)

    @Query("DELETE FROM dreamentity where id IN (:dreamId)")
    suspend fun deleteDreamById(dreamId: Long)
}
