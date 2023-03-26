package com.bloom.dailywisdom.data

import javax.inject.Inject

class BloomDataSourceImpl @Inject constructor(private val apiService: BloomApiService) : BloomDataSource {
    override suspend fun getBloomData(): BloomDataResponse {
        return apiService.getBloomData()
    }
}