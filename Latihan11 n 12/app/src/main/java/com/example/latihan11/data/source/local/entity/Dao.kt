package com.example.latihan11.data.source.local.entity

import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    //Game
    @Query("SELECT * FROM tb_game")
    fun getLocalGames(): DataSource.Factory<Int, GameEntity>

    @Query("SELECT * FROM tb_game WHERE favorite = 1")
    fun getFavoriteGame(): DataSource.Factory<Int, GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: List<GameEntity>)

    @Update
    fun updateGames(games: GameEntity)

    //Kartun
    @Query("SELECT * FROM tb_kartun")
    fun getLocalKartun(): DataSource.Factory<Int, KartunEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKartun(kartun: List<KartunEntity>)
}