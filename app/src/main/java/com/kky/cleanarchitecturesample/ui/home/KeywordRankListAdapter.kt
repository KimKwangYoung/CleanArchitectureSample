package com.kky.cleanarchitecturesample.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kky.cleanarchitecturesample.databinding.ItemKeywordRankBinding
import com.kky.domain.model.Keyword

class KeywordRankListAdapter: ListAdapter<Keyword, KeywordRankListAdapter.KeywordRankViewHolder>(KeywordDiffUtil()) {

    class KeywordRankViewHolder(private val binding: ItemKeywordRankBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Keyword) {
            binding.tvKeyword.text = item.value
            binding.tvInputCount.text = item.count.toString()
        }

    }

    private class KeywordDiffUtil: DiffUtil.ItemCallback<Keyword>() {
        override fun areItemsTheSame(oldItem: Keyword, newItem: Keyword): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Keyword, newItem: Keyword): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordRankViewHolder =
        KeywordRankViewHolder(
            ItemKeywordRankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: KeywordRankViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}