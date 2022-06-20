package com.example.latihan61.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan61.data.remote.response.home.ResponseHomeItem
import com.example.latihan61.repository.Repository

class HomeViewModel(private val homeRepository: Repository) : ViewModel() {
    fun getUsers(): LiveData<List<ResponseHomeItem>> = homeRepository.getUsers()
}