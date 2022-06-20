package com.example.latihan11.ui.kartun

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.latihan11.data.source.Repository
import com.example.latihan11.data.source.local.entity.KartunEntity
import com.example.latihan11.vo.Resource

class KartunViewModel(private val kartunRepository: Repository): ViewModel() {

    fun getKartun(): LiveData<Resource<PagedList<KartunEntity>>> = kartunRepository.getKartun()
}