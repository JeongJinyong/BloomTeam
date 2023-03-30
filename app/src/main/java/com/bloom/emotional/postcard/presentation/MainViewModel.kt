package com.bloom.emotional.postcard.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bloom.emotional.postcard.domain.GetBloomDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val getBloomDataUseCase: GetBloomDataUseCase
) : ViewModel() {

    private val _history = MutableStateFlow<List<String>>(listOf())
    val history: StateFlow<List<String>> = _history

    init {
        getLinks()
    }

    fun setShare() {
        viewModelScope.launch {
            getBloomDataUseCase.setShare()
        }
    }

    fun setPush(key: String) {
        viewModelScope.launch {
            getBloomDataUseCase.setPush(key)
        }
    }


    fun saveLinks(link: String) {
        if (!history.value.contains(link)) {
            val editor = sharedPreferences.edit()
            editor.putStringSet("stories", HashSet(history.value.toMutableList().apply {
                add(link)
            }))
            editor.apply()
            getLinks()
        }
    }

    private fun getLinks() {
        val linkSet = sharedPreferences.getStringSet("stories", HashSet())
        _history.value = linkSet?.toList() ?: listOf()
    }

}