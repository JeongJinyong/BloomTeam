package com.bloom.emotional.postcard.data

import com.bloom.emotional.postcard.data.BloomApiService
import com.bloom.emotional.postcard.data.BloomDataResponse
import com.bloom.emotional.postcard.data.BloomDataSource
import javax.inject.Inject

class BloomRemoteDataSource @Inject constructor(
    private val apiService: BloomApiService
) : BloomDataSource {
    override suspend fun getBloomData(): BloomDataResponse {
        return apiService.getBloomData()
    }
}