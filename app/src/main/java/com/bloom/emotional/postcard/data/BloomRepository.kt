package com.bloom.emotional.postcard.data

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class BloomRepository @Inject constructor(
    private val remoteDataSource: BloomDataSource,
    private val sharedPreferences: SharedPreferences
) {
    companion object{
        const val KEY_MY_DATA = "bloom_data"
    }
    suspend fun getBloomData(): BloomDataResponse {
        // Check if data is cached in shared preferences
        val cachedData = sharedPreferences.getString(KEY_MY_DATA, null)
        if (cachedData != null) {
            return Gson().fromJson(cachedData, BloomDataResponse::class.java)
        }

        // If not, fetch data from remote data source
        val data = remoteDataSource.getBloomData()

        // Cache data in shared preferences
        sharedPreferences.edit().putString(KEY_MY_DATA, Gson().toJson(data)).apply()

        return data
    }
}