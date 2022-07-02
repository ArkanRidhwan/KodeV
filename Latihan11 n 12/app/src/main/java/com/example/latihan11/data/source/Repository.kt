package com.example.latihan11.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.latihan11.data.source.local.LocalDataSource
import com.example.latihan11.data.source.local.entity.GameEntity
import com.example.latihan11.data.source.local.entity.KartunEntity
import com.example.latihan11.data.source.remote.ApiResponse
import com.example.latihan11.data.source.remote.RemoteDataSource
import com.example.latihan11.data.source.remote.response.game.ResponseGame
import com.example.latihan11.data.source.remote.response.kartun.ResponseKartun
import com.example.latihan11.utils.AppExecutors
import com.example.latihan11.vo.Resource

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
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                ).apply {
                    instance = this
                }
            }
    }

    override fun getGames(): LiveData<Resource<PagedList<GameEntity>>> {
        return object : NetworkBoundResource<PagedList<GameEntity>, ResponseGame>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<GameEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getLocalGame(), config).build()
            }

            override fun shouldFetch(data: PagedList<GameEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseGame>> {
                return remoteDataSource.getGames()
            }

            override fun saveCallResult(data: ResponseGame) {
                val listGame = ArrayList<GameEntity>()

                for (i in data.results) {
                    val listPlatform = ArrayList<String>()
                    val listGenre = ArrayList<String>()
                    var recommended = ""
                    var minimum = ""

                    i.platforms.map {
                        if (it.platform.name == "PC") {
                            minimum = it.requirements_en?.minimum.toString()
                            recommended = it.requirements_en?.recommended.toString()
                        }
                        listPlatform.add((it.platform.name))
                    }
                    i.genres.map {
                        listGenre.add(it.name)
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

    override fun getFavoritesGames(): LiveData<PagedList<GameEntity>> {
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

            override fun shouldFetch(data: PagedList<KartunEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseKartun>> {
                return remoteDataSource.getKartun()
            }

            override fun saveCallResult(data: ResponseKartun) {
                val listKartun = ArrayList<KartunEntity>()

                for (i in data.results) {
                    val kartun = KartunEntity(
                        i.id,
                        i.image,
                        i.species,
                        i.name,
                        i.status,
                        i.origin.name,
                        i.created
                    )
                    listKartun.add(kartun)
                }
                localDataSource.insertKartun(listKartun)
            }
        }.asLiveData()
    }
}