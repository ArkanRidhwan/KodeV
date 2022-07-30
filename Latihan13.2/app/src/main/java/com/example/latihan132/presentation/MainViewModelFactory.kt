package com.example.latihan132.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan132.di.Injection
import com.example.latihan132.domain.IKartunUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val iKartunUseCase: IKartunUseCase
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance(context: Context): MainViewModelFactory = instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(Injection.provideKartunUseCase(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                iKartunUseCase
            ) as T
            else -> {
                throw Throwable("Unknown ViewModel Class" + modelClass.name)
            }
        }
    }
}