package com.example.mylivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var _nomor = MutableLiveData<String>()
    var nomor: LiveData<String> = _nomor

    /*fun setText(text: String)  {
        _nomor.value = text
    }*/
}