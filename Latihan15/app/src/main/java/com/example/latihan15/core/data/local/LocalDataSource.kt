package com.example.latihan15.core.data.local

import com.example.latihan15.core.data.local.room.MainDao
import com.example.latihan15.core.data.local.room.MakeupEntity
import com.example.latihan15.core.data.local.room.StoryEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mainDao: MainDao) {

    /*companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mainDao: MainDao) =
            INSTANCE ?: LocalDataSource(mainDao)
    }*/

    fun getLocalMakeup(): Flow<List<MakeupEntity>> = mainDao.getLocalMakeUp()

    suspend fun insertMakeup(listData: List<MakeupEntity>) = mainDao.insertMakeUp(listData)

    fun getLocalStory(): Flow<List<StoryEntity>> = mainDao.getLocalStory()

    suspend fun insertStory(listData: List<StoryEntity>) = mainDao.insertStory(listData)
}