package com.example.latihan131.domain.repository

import androidx.lifecycle.LiveData
import com.example.latihan131.data.datasource.remote.response.kartun.ResponseKartun
import com.example.latihan131.domain.model.Kartun

interface IRepository {
    fun createCall(): LiveData<List<Kartun>>
}