package com.example.latihan13.core.data.source.local.entity

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    //Game
    @Query("SELECT * FROM tb_game")
    fun getLocalGame(): LiveData<List<GameEntity>>

    @Query("SELECT * FROM tb_game WHERE favorite = 1")
    fun getFavoriteGame(): LiveData<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: List<GameEntity>)

    @Update
    fun updateGame(game: GameEntity)

    //Kartun
    @Query("SELECT * FROM tb_kartun")
    fun getLocalKartun(): LiveData<List<KartunEntity>>

    @Query("SELECT * FROM tb_kartun WHERE favorite = 1")
    fun getFavoriteKartun(): DataSource.Factory<Int, KartunEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKartun(kartun: List<KartunEntity>)

    @Update
    fun updateKartun(kartun: KartunEntity)

    //Nasa
    @Query("SELECT * FROM tb_nasa")
    fun getLocalNasa(): LiveData<List<NasaEntity>>

    @Query("SELECT * FROM tb_nasa WHERE favorite = 1")
    fun getFavoriteNasa(): DataSource.Factory<Int, NasaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNasa(nasa: List<NasaEntity>)

    @Update
    fun updateNasa(nasa: NasaEntity)

    //Meme
    @Query("SELECT * FROM tb_meme")
    fun getLocalMeme(): LiveData<List<MemeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeme(meme: List<MemeEntity>)
}