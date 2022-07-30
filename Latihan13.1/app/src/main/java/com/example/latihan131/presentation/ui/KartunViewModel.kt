package com.example.latihan131.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.latihan131.R
import com.example.latihan131.domain.model.Kartun
import com.example.latihan131.domain.usecases.IKartunUseCase


class KartunViewModel(private val iKartunUseCase: IKartunUseCase) : ViewModel() {

    fun createCall(): LiveData<List<Kartun>> = iKartunUseCase.createCall()

}