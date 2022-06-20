package com.example.latihan121.data.source.local

import androidx.paging.DataSource
import com.example.latihan121.data.source.local.entity.Dao
import com.example.latihan121.data.source.local.entity.GameEntity
import com.example.latihan121.data.source.local.entity.KartunEntity
import com.example.latihan121.data.source.local.entity.NasaEntity

class LocalDataSource private constructor(private val dao: Dao) {

    companion object {
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(dao: Dao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao)
    }

    fun getLocalGame(): DataSource.Factory<Int, GameEntity> = dao.getLocalGame()
    fun getFavoriteGame(): DataSource.Factory<Int, GameEntity> = dao.getFavoriteGame()
    fun insertGame(listGameEntity: List<GameEntity>) = dao.insertGame(listGameEntity)
    fun updateGame(gameEntity: GameEntity, newState: Boolean) {
        gameEntity.favorite = newState
        dao.updateGame(gameEntity)
    }

    fun getLocalKartun(): DataSource.Factory<Int, KartunEntity> = dao.getLocalKartun()
    fun getFavoriteKartun(): DataSource.Factory<Int, KartunEntity> = dao.getFavoriteKartun()
    fun insertKartun(listKartunEntity: List<KartunEntity>) = dao.insertKartun(listKartunEntity)
    fun updateKartun(kartunEntity: KartunEntity, newState: Boolean) {
        kartunEntity.favorite = newState
        dao.updateKartun(kartunEntity)
    }

    fun getLocalNasa(): DataSource.Factory<Int, NasaEntity> = dao.getLocalNasa()
    fun getFavoriteNasa(): DataSource.Factory<Int, NasaEntity> = dao.getFavoriteNasa()
    fun insertNasa(listNasaEntity: List<NasaEntity>) = dao.insertNasa(listNasaEntity)
    fun updateNasa(nasaEntity: NasaEntity, newState: Boolean){
        nasaEntity.favorite = newState
        dao.updateNasa(nasaEntity)
    }
}