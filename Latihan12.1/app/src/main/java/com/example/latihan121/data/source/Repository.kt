package com.example.latihan121.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.latihan121.data.source.local.LocalDataSource
import com.example.latihan121.data.source.local.entity.GameEntity
import com.example.latihan121.data.source.local.entity.KartunEntity
import com.example.latihan121.data.source.local.entity.NasaEntity
import com.example.latihan121.data.source.remote.response.ApiResponse
import com.example.latihan121.data.source.remote.response.RemoteDataSource
import com.example.latihan121.data.source.remote.response.game.ResponseGame
import com.example.latihan121.data.source.remote.response.kartun.ResponseKartun
import com.example.latihan121.data.source.remote.response.nasa.ResponseNasaItem
import com.example.latihan121.utils.AppExecutors
import com.example.latihan121.vo.Resource

class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DataSource {

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

    override fun getGame(): LiveData<Resource<PagedList<GameEntity>>> {
        return object : NetworkBoundResource<PagedList<GameEntity>, ResponseGame>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<GameEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getLocalGame(), config).build()
            }

            override fun shouldFetch(it: PagedList<GameEntity>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseGame>> {
                return remoteDataSource.getGame()
            }

            override fun onFetchFailed() {
                TODO("Not yet implemented")
            }

            override fun saveCallResult(it: ResponseGame) {
                val listGame = ArrayList<GameEntity>()

                for (i in it.results) {
                    val listPlatform = ArrayList<String>()
                    val listGenre = ArrayList<String>()
                    var recommended = ""
                    var minimum = ""

                    for (i in i.platforms) {
                        if (i.platform.name == "PC") {
                            minimum = i.requirements_en?.minimum.toString()
                            recommended = i.requirements_en?.recommended.toString()
                        }
                        listPlatform.add(i.platform.name)
                    }

                    for (i in i.genres) {
                        listGenre.add(i.name)
                    }

                    val game = GameEntity(
                        i.id,
                        i.name,
                        i.released,
                        i.background_image,
                        i.rating.toString(),
                        listPlatform.toString(),
                        listGenre.toString(),
                        minimum, recommended
                    )
                    listGame.add(game)
                }
                localDataSource.insertGame(listGame)
            }
        }.asLiveData()
    }

    override fun getFavoriteGame(): LiveData<PagedList<GameEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteGame(), config).build()
    }

    override fun updateGame(game: GameEntity, newState: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateGame(game, newState)
        }
    }

    override fun getKartun(): LiveData<Resource<PagedList<KartunEntity>>> {
        return object :
            NetworkBoundResource<PagedList<KartunEntity>, ResponseKartun>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<KartunEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getLocalKartun(), config).build()
            }

            override fun shouldFetch(it: PagedList<KartunEntity>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseKartun>> {
                return remoteDataSource.getKartun()
            }

            override fun onFetchFailed() {
                TODO("Not yet implemented")
            }

            override fun saveCallResult(it: ResponseKartun) {
                val listKartun = ArrayList<KartunEntity>()

                for (i in it.results) {
                    val kartun = KartunEntity(
                        i.id,
                        i.image,
                        i.species,
                        i.name,
                        i.status,
                        i.origin.name,
                        i.created,
                    )
                    listKartun.add(kartun)
                }
                localDataSource.insertKartun(listKartun)
            }
        }.asLiveData()
    }

    override fun getFavoriteKartun(): LiveData<Resource<PagedList<KartunEntity>>> {
        TODO("Not yet implemented")
    }

    override fun updateKartun(kartun: KartunEntity, newState: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getNasa(): LiveData<Resource<List<NasaEntity>>> {
        return object :
            NetworkBoundResource<List<NasaEntity>, List<ResponseNasaItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<NasaEntity>> {
                return localDataSource.getLocalNasa()
            }

            override fun shouldFetch(it: List<NasaEntity>?): Boolean {
                return it == null || it.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResponseNasaItem>>> {
                return remoteDataSource.getNasa()
            }

            override fun onFetchFailed() {
                TODO("Not yet implemented")
            }

            override fun saveCallResult(it: List<ResponseNasaItem>) {
                val listNasa = ArrayList<NasaEntity>()
                for (i in it) {
                    val nasa = NasaEntity(
                        title = i.title,
                        copyright = i.copyright?:"No Copyright",
                        hdurl = i.hdurl,
                        date = i.date,
                        explanation = i.explanation
                    )

                    listNasa.add(nasa)
                }
                localDataSource.insertNasa(listNasa)
            }
        }.asLiveData()
    }

    override fun getFavoriteNasa(): LiveData<Resource<PagedList<NasaEntity>>> {
        TODO("Not yet implemented")
    }

    override fun updateNasa(nasa: NasaEntity, newState: Boolean) {
        TODO("Not yet implemented")
    }
}