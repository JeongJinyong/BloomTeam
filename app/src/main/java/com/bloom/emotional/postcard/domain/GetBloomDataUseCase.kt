package com.bloom.emotional.postcard.domain

import com.bloom.emotional.postcard.data.BloomDataResponse
import com.bloom.emotional.postcard.data.BloomRepository
import javax.inject.Inject

class GetBloomDataUseCase @Inject constructor(
    private val bloomRepository: BloomRepository
) {
    suspend operator fun invoke(): BloomDataResponse {
        return bloomRepository.getBloomData()
    }
}