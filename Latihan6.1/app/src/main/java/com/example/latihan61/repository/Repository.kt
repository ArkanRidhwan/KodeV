package com.example.latihan61.repository

import androidx.lifecycle.MutableLiveData
import com.example.latihan61.data.DataSource
import com.example.latihan61.data.remote.RemoteRepository
import com.example.latihan61.data.remote.response.home.ResponseHomeItem
import com.example.latihan61.data.remote.response.kartun.KartunResponse

class Repository private constructor(private val remoteRepository: RemoteRepository) :
    DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteRepository: RemoteRepository): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteRepository).apply {
                    instance = this
                }
            }
    }

    override fun getUsers(): MutableLiveData<List<ResponseHomeItem>> {
        val getUsers = MutableLiveData<List<ResponseHomeItem>>()
        remoteRepository.getHome(object : RemoteRepository.LoadGetHome {
            override fun onLoadGetUsers(listUser: List<ResponseHomeItem>) {
                getUsers.value = listUser
            }
        })
        return getUsers
    }

    override fun getCharacters(): MutableLiveData<KartunResponse>{
        val getKartuns = MutableLiveData<KartunResponse>()
        remoteRepository.getKartuns(object : RemoteRepository.LoadGetKartun{
            override fun onLoadGetCharacters(listCharacters: KartunResponse) {
                getKartuns.value = listCharacters
            }
        })
        return getKartuns
    }
}