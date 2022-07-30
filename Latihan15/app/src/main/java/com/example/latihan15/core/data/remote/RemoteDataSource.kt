package com.example.latihan15.core.data.remote

import android.util.Log
import com.example.latihan15.core.data.remote.api.ApiConfig
import com.example.latihan15.core.data.remote.response.dicoding.getStory.ResponseStory
import com.example.latihan15.core.data.remote.response.dicoding.postStory.ResponsePostStory
import com.example.latihan15.core.data.remote.response.makeup.ResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    suspend fun getMakeup(): Flow<ApiResponse<List<ResponseItem>>> {
        return flow {
            try {
                val response = ApiConfig.getApiServiceMakeUp().getMakeup("maybelline")
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    ApiResponse.Empty
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getMakeup: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getStory(): Flow<ApiResponse<ResponseStory>> {
        return flow {
            try {
                val response = ApiConfig.getApiServiceStory().getStory(
                    1,
                    50,
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLWRIVHp2UUtDWTVuYXotejkiLCJpYXQiOjE2NTg4OTk0MDN9.0OOS-9zckZBV2VA55i8MW7a3DUKaC_8BBwsAGIBNzUY"
                )
                if (response.listStory.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    ApiResponse.Empty
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getStory $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun postStory(): Flow<ApiResponse<ResponsePostStory>> {
        return flow<ApiResponse<ResponsePostStory>> {
            try {
                /*val response = ApiConfig.getApiServiceStory().postStory(
                    "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLWRIVHp2UUtDWTVuYXotejkiLCJpYXQiOjE2NTg4OTk0MDN9.0OOS-9zckZBV2VA55i8MW7a3DUKaC_8BBwsAGIBNzUY",

                    )*/
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "postStory: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}