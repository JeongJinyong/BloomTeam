package com.bloom.dailywisdom.domain

import com.bloom.dailywisdom.data.BloomDataResponse
import com.bloom.dailywisdom.data.BloomRepository
import javax.inject.Inject

class GetBloomDataUseCase @Inject constructor(
    private val bloomRepository: BloomRepository
) {
    suspend operator fun invoke(): BloomDataResponse {
        return bloomRepository.getBloomData()
    }
}