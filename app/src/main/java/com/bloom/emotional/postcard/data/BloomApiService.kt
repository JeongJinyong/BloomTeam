package com.bloom.emotional.postcard.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BloomApiService {

    @GET("/bloom/push")
    suspend fun setPushKey(@Query("date") key: String): Response<Unit>

    @GET("/bloom/share")
    suspend fun setShare(@Query("date") date: String): Response<Unit>
}