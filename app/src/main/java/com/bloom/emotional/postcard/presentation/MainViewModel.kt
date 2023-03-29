package com.bloom.emotional.postcard.presentation

import androidx.lifecycle.ViewModel
import com.bloom.emotional.postcard.domain.GetBloomDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBloomDataUseCase: GetBloomDataUseCase
) : ViewModel() {
}