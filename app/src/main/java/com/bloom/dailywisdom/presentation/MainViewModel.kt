package com.bloom.dailywisdom.presentation

import androidx.lifecycle.ViewModel
import com.bloom.dailywisdom.domain.GetBloomDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBloomDataUseCase: GetBloomDataUseCase
) : ViewModel() {
}