package com.example.latihan11.data.source.remote

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {

    //Setiap baris peratama diatas
    companion object{
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCES, body, null)

        fun <T> empty(msg: String, body: T): ApiResponse<T> =
            ApiResponse(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String, body: T): ApiResponse<T> =
            ApiResponse(StatusResponse.ERROR,body, msg)
    }
}