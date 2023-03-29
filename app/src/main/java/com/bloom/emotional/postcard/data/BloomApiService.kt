package com.bloom.emotional.postcard.data

import retrofit2.http.GET

interface BloomApiService {

    @GET("test")
    suspend fun getBloomData(): BloomDataResponse

}