package com.example.latihan121.ui.kartun

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.latihan121.data.source.Repository
import com.example.latihan121.data.source.local.entity.KartunEntity
import com.example.latihan121.vo.Resource

class KartunViewModel(private val repository: Repository) : ViewModel() {

    fun getKartun(): LiveData<Resource<PagedList<KartunEntity>>> = repository.getKartun()
}