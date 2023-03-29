package com.bloom.emotional.postcard.data

import com.bloom.emotional.postcard.data.BloomDataResponse

interface BloomDataSource {
    suspend fun getBloomData(): BloomDataResponse
}