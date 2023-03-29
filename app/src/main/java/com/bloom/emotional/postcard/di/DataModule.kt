package com.bloom.emotional.postcard.di

import android.content.Context
import android.content.SharedPreferences
import com.bloom.emotional.postcard.data.BloomApiService
import com.bloom.emotional.postcard.data.BloomDataSource
import com.bloom.emotional.postcard.data.BloomDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideBloomDataSource(apiService: BloomApiService): BloomDataSource {
        return BloomDataSourceImpl(apiService)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    }
}