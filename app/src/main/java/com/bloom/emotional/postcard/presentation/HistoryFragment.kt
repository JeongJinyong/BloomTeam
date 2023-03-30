package com.bloom.emotional.postcard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bloom.emotional.postcard.base.BaseFragment
import com.bloom.emotional.postcard.databinding.FragmentHistoryBinding
import com.bloom.emotional.postcard.imageLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    private val viewModel: MainViewModel by activityViewModels()
    private val adapter by lazy { HistoryAdapter() }
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerHistory.adapter = adapter
        adapter.setItemClick {
            binding.groupHistoryDetail.isVisible = true
            binding.imgHistoryDetail.imageLoad(it)
        }
        binding.imgClose.setOnClickListener {
            binding.groupHistoryDetail.isVisible = false
        }
        adapter.setItems(viewModel.history.value)
        lifecycleScope.launch {
            viewModel.history.collect {
                adapter.setItems(it)
            }
        }
    }
}