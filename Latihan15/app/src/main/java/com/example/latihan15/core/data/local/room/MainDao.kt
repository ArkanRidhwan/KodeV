package com.example.latihan15.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {
    //Makeup
    @Query("SELECT * FROM tb_makeup")
    fun getLocalMakeUp(): Flow<List<MakeupEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMakeUp(listData: List<MakeupEntity>)

    //Discord
    @Query("SELECT * FROM tb_dicoding")
    fun getLocalStory(): Flow<List<StoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(listData: List<StoryEntity>)
}