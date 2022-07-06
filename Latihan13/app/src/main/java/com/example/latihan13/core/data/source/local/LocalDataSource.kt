package com.example.latihan13.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.latihan13.core.data.source.local.entity.*

class LocalDataSource private constructor(private val dao: Dao) {

    companion object {
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(dao: Dao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao)
    }

    //Meme
    fun getLocalGame() = dao.getLocalGame()
    fun getFavoriteGame() = dao.getFavoriteGame()
    fun insertGame(listGameEntity: List<GameEntity>) = dao.insertGame(listGameEntity)
    fun updateGame(gameEntity: GameEntity, newState: Boolean) {
        gameEntity.favorite = newState
        dao.updateGame(gameEntity)
    }

    //Kartun
    fun getLocalKartun() = dao.getLocalKartun()
    fun getFavoriteKartun(): DataSource.Factory<Int, KartunEntity> = dao.getFavoriteKartun()
    fun insertKartun(listKartunEntity: List<KartunEntity>) = dao.insertKartun(listKartunEntity)
    fun updateKartun(kartunEntity: KartunEntity, newState: Boolean) {
        kartunEntity.favorite = newState
        dao.updateKartun(kartunEntity)
    }

    //Nasa
    fun getLocalNasa(): LiveData<List<NasaEntity>> = dao.getLocalNasa()
    fun getFavoriteNasa(): DataSource.Factory<Int, NasaEntity> = dao.getFavoriteNasa()
    fun insertNasa(listNasaEntity: List<NasaEntity>) = dao.insertNasa(listNasaEntity)
    fun updateNasa(nasaEntity: NasaEntity, newState: Boolean){
        nasaEntity.favorite = newState
        dao.updateNasa(nasaEntity)
    }

    //Meme
    fun getLocalMeme(): LiveData<List<MemeEntity>> = dao.getLocalMeme()
    fun insertMeme(listMemeEntity: List<MemeEntity>) = dao.insertMeme(listMemeEntity)
}