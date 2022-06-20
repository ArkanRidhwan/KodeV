package com.example.latihan61.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan61.di.Injection
import com.example.latihan61.repository.Repository
import com.example.latihan61.ui.home.HomeViewModel
import com.example.latihan61.ui.kartun.KartunViewModel

class ViewModelFactory private constructor(private val homeRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(homeRepository) as T
            }
            modelClass.isAssignableFrom(KartunViewModel::class.java) -> {
                return KartunViewModel(homeRepository) as T
            }
            else -> throw Throwable("Uknown ViewModel class" + modelClass.name)
        }
    }
}