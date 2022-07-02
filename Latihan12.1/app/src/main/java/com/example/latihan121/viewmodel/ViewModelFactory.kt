package com.example.latihan121.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan121.data.source.Repository
import com.example.latihan121.di.Injection
import com.example.latihan121.ui.game.GameViewModel
import com.example.latihan121.ui.kartun.KartunViewModel
import com.example.latihan121.ui.nasa.NasaViewModel

class ViewModelFactory private constructor(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

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

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(GameViewModel::class.java) -> return GameViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(KartunViewModel::class.java) -> return KartunViewModel(
                repository
            ) as T
            modelClass.isAssignableFrom(NasaViewModel::class.java) -> return NasaViewModel(
                repository
            ) as T
            else -> throw  Throwable("Unknown ViewModel Class" + modelClass.name)
        }
    }
}