package com.bloom.emotional.postcard.data

interface BloomDataSource {
    suspend fun setPushKey(key: String)
    suspend fun setShare()
}