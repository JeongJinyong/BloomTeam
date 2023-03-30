package com.bloom.emotional.postcard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bloom.emotional.postcard.base.BaseFragment
import com.bloom.emotional.postcard.databinding.FragmentStoriesBinding
import com.bloom.emotional.postcard.imageLoad
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoriesFragment : BaseFragment<FragmentStoriesBinding>() {
    private val viewModel: MainViewModel by activityViewModels()
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentStoriesBinding {
        return FragmentStoriesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgStories.imageLoad("http://118.67.135.198:8000/bloom/content?date=2023-03-24&img_ext=JPG")
    }
}