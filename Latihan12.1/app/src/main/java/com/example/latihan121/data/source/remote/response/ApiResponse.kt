package com.example.latihan121.data.source.remote.response

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T) = ApiResponse(StatusResponse.SUCCES, body, null)

        fun <T> empty(msg: String, body: T) = ApiResponse(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String, body: T) = ApiResponse(StatusResponse.ERROR, body, msg)
    }
}