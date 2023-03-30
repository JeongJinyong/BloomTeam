package com.bloom.emotional.postcard.data

import javax.inject.Inject

class BloomRepository @Inject constructor(private val remoteDataSource: BloomDataSource) {
    suspend fun setPush(key: String) {
        remoteDataSource.setPushKey(key)
    }

    suspend fun setShare() {
        remoteDataSource.setShare()
    }
}