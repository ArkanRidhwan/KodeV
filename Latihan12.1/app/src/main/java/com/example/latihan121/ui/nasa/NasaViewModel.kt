package com.example.latihan121.ui.nasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.latihan121.data.source.Repository
import com.example.latihan121.data.source.local.entity.NasaEntity
import com.example.latihan121.vo.Resource

class NasaViewModel(private val repository: Repository): ViewModel() {

    fun getNasa(): LiveData<Resource<List<NasaEntity>>> = repository.getNasa()
}