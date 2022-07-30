package com.example.latihan14.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinasiDao {
    @Query("SELECT * FROM tb_destinasi")
    fun getLocalDestinasi(): Flow<List<DestinasiEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(destinasi: List<DestinasiEntity>)
}