package com.example.latihan13.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.latihan13.core.data.source.local.LocalDataSource
import com.example.latihan13.core.data.source.remote.response.ApiResponse
import com.example.latihan13.core.data.source.remote.response.RemoteDataSource
import com.example.latihan13.core.data.source.remote.response.game.ResponseGame
import com.example.latihan13.core.data.source.remote.response.kartun.ResponseKartun
import com.example.latihan13.core.data.source.remote.response.meme.Data
import com.example.latihan13.core.data.source.remote.response.nasa.ResponseNasaItem
import com.example.latihan13.core.domain.model.Game
import com.example.latihan13.core.domain.model.Kartun
import com.example.latihan13.core.domain.model.Meme
import com.example.latihan13.core.domain.model.Nasa
import com.example.latihan13.core.domain.repository.IRepository
import com.example.latihan13.utils.AppExecutors
import com.example.latihan13.utils.DataMapper
import com.example.latihan13.vo.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRepository {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): Repository = instance ?: synchronized(this) {
            instance ?: Repository(
                remoteDataSource,
                localDataSource,
                appExecutors
            ).apply {
                instance = this
            }
        }
    }

    override fun getGame(): LiveData<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, ResponseGame>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Game>> {
                return Transformations.map(localDataSource.getLocalGame()) {
                    DataMapper.mapGameEntityToDomain(it)
                }
            }

            override fun shouldFetch(it: List<Game>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseGame>> {
                return remoteDataSource.getGame()
            }

            override fun saveCallResult(it: ResponseGame) {
                val listGame = DataMapper.mapResponseGameToEntities(it)
                localDataSource.insertGame(listGame)
            }
        }.asLiveData()
    }

    override fun getFavoriteGame(): LiveData<List<Game>> {
        return Transformations.map(localDataSource.getFavoriteGame()) {
            DataMapper.mapGameEntityToDomain(it)
        }
    }

    override fun updateGame(game: Game, newState: Boolean) {
        val gameEntity = DataMapper.mapDomainToGameEntity(game)
        appExecutors.diskIO().execute {
            localDataSource.updateGame(gameEntity, newState)
        }
    }

    override fun getKartun(): LiveData<Resource<List<Kartun>>> {
        return object :
            NetworkBoundResource<List<Kartun>, ResponseKartun>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Kartun>> {
                return Transformations.map(localDataSource.getLocalKartun()) {
                    DataMapper.mapKartunEntityToDomain(it)
                }
            }

            override fun shouldFetch(it: List<Kartun>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseKartun>> {
                return remoteDataSource.getKartun()
            }


            override fun saveCallResult(it: ResponseKartun) {
                val listKartun = DataMapper.mapResponseKartunToEntities(it)
                localDataSource.insertKartun(listKartun)
            }
        }.asLiveData()
    }

    override fun getFavoriteKartun(): LiveData<Resource<List<Kartun>>> {
        TODO("Not yet implemented")
    }

    override fun updateKartun(kartun: Kartun, newState: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getNasa(): LiveData<Resource<List<Nasa>>> {
        return object :
            NetworkBoundResource<List<Nasa>, List<ResponseNasaItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Nasa>> {
                return Transformations.map(localDataSource.getLocalNasa()) {
                    DataMapper.mapNasaEntityToDomain(it)
                }
            }

            override fun shouldFetch(it: List<Nasa>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResponseNasaItem>>> {
                return remoteDataSource.getNasa()
            }


            override fun saveCallResult(it: List<ResponseNasaItem>) {
                val listNasa = DataMapper.mapResponseNasaToEntities(it)
                localDataSource.insertNasa(listNasa)
            }
        }.asLiveData()
    }

    override fun getFavoriteNasa(): LiveData<Resource<List<Nasa>>> {
        TODO("Not yet implemented")
    }

    override fun updateNasa(nasa: Nasa, newState: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getMeme(): LiveData<Resource<List<Meme>>> {
        return object : NetworkBoundResource<List<Meme>, Data>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Meme>> {
                return Transformations.map(localDataSource.getLocalMeme()) {
                    DataMapper.mapMemeEntityToDomain(it)
                }
            }

            override fun shouldFetch(it: List<Meme>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<Data>> {
                return remoteDataSource.getMeme()
            }

            override fun saveCallResult(it: Data) {
                val lisMeme = DataMapper.mapResponseMemeToEntities(it)
                localDataSource.insertMeme(lisMeme)
            }

        }.asLiveData()
    }
}