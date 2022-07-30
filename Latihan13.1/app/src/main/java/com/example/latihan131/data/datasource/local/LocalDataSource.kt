package com.example.latihan131.data.datasource.local

import com.example.latihan131.data.datasource.local.entity.DAO

class LocalDataSource(private val dao: DAO) {
    fun getLocalKartun() = dao.getLocalKartun()
}