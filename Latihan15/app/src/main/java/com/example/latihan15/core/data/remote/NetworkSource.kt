package com.example.latihan15.core.data.remote

import com.example.latihan15.core.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkSource<ResultType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when(val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                onFetchFailed()
                emit(Resource.Error("Empty Data"))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }

    protected fun onFetchFailed(){}
    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>
    fun asFlow(): Flow<Resource<ResultType>> = result
}