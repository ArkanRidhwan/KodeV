package com.example.latihan14.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan14.core.di.Injection
import com.example.latihan14.core.domain.usecase.IDestinasiUseCase
import com.example.latihan14.ui.main.MainFragment
import com.example.latihan14.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val iDestinasiUseCase: IDestinasiUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideKartunUseCase(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                iDestinasiUseCase
            ) as T
            else -> {
                throw Throwable("Unknown ViewModel Class" + modelClass.name)
            }
        }
    }
}