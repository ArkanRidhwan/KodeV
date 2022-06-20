package com.example.latihan11.data.source.local

import androidx.paging.DataSource
import com.example.latihan11.data.source.local.entity.GameEntity
import com.example.latihan11.data.source.local.entity.Dao
import com.example.latihan11.data.source.local.entity.KartunEntity

class LocalDataSource private constructor(private val dao: Dao) {

    companion object{
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(dao: Dao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao)

    }

    //Ini buat apa kak?
    fun getLocalGame(): DataSource.Factory<Int, GameEntity> = dao.getLocalGames()

    fun getFavoriteGame(): DataSource.Factory<Int, GameEntity> = dao.getFavoriteGame()

    fun insertGame(listGame: List<GameEntity>) = dao.insertGame(listGame)

    fun updateGame(game: GameEntity, newState: Boolean){
        game.favorite = newState
        dao.updateGames(game)
    }


    fun getLocalKartun(): DataSource.Factory<Int, KartunEntity> = dao.getLocalKartun()

    fun insertKartun(listKartun: List<KartunEntity>) = dao.insertKartun(listKartun)
}