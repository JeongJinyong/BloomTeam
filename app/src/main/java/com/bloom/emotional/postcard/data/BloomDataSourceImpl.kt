package com.bloom.emotional.postcard.data

import com.bloom.emotional.postcard.getCurrentTime
import javax.inject.Inject

class BloomDataSourceImpl @Inject constructor(private val apiService: BloomApiService) : BloomDataSource {


    override suspend fun setPushKey(key: String) {
        apiService.setPushKey(key)
    }

    override suspend fun setShare() {
        apiService.setShare(getCurrentTime())
    }
}