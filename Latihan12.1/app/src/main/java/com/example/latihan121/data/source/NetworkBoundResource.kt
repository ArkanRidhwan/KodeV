package com.example.latihan121.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.latihan121.data.source.remote.response.ApiResponse
import com.example.latihan121.data.source.remote.response.StatusResponse
import com.example.latihan121.utils.AppExecutors
import com.example.latihan121.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        val dbSource = loadFromDB()

        result.addSource(dbSource) {
            result.removeSource(dbSource)
            if (shouldFetch(it)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) {
                    result.value = Resource.success(it)
                }
            }
        }
    }

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(it: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    abstract fun onFetchFailed()

    abstract fun saveCallResult(it: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) {
            result.value = Resource.loading(it)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.status) {
                StatusResponse.SUCCES -> mExecutor.diskIO().execute {
                    saveCallResult(response.body)
                    mExecutor.mainThread().execute {
                        result.addSource(loadFromDB()) {
                            result.value = Resource.success(it)
                        }
                    }
                }
                StatusResponse.EMPTY -> mExecutor.mainThread().execute {
                    result.addSource(loadFromDB()) {
                        result.value = Resource.success(it)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        result.value = Resource.error(response.message, it)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}