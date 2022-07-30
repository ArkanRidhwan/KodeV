package com.example.latihan131.data.datasource.local.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DAO {
    @Query("SELECT * FROM  tb_kartun")
    fun getLocalKartun(): LiveData<List<KartunEntity>>
}