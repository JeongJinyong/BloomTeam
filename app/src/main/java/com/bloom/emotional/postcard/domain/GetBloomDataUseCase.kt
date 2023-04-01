package com.bloom.emotional.postcard.domain

import com.bloom.emotional.postcard.data.BloomRepository
import javax.inject.Inject

class GetBloomDataUseCase @Inject constructor(
    private val bloomRepository: BloomRepository
) {
    suspend fun setShare() {
        bloomRepository.setShare()
    }

    suspend fun setPush(key: String) {
        bloomRepository.setPush(key)
    }
}