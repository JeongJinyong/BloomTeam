package com.bloom.dailywisdom.data

interface BloomDataSource {
    suspend fun getBloomData(): BloomDataResponse
}