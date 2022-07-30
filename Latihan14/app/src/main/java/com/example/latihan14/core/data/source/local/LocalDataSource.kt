package com.example.latihan14.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.latihan14.core.data.source.local.room.DestinasiDao
import com.example.latihan14.core.data.source.local.room.DestinasiEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val destinasiDao: DestinasiDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance2(destinasiDao: DestinasiDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(destinasiDao)
    }

    fun getLocalDestinasi(): Flow<List<DestinasiEntity>> = destinasiDao.getLocalDestinasi()

    suspend fun insertDestinasi(listData : List<DestinasiEntity>) = destinasiDao.insertGame(listData)
}