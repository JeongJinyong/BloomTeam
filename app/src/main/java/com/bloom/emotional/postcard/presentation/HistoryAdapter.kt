package com.bloom.emotional.postcard.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bloom.emotional.postcard.databinding.ItemHistoryBinding
import com.bloom.emotional.postcard.imageLoad

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val items = mutableListOf<String>()
    private var itemClick: (String) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bind)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(item: List<String>) {
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }

    fun setItemClick(click: (String) -> Unit) {
        itemClick = click
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBindView(data: String) {
            binding.imgHistory.imageLoad(data)
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }

}