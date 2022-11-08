package com.kky.cleanarchitecturesample.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kky.cleanarchitecturesample.databinding.ItemPostCardBinding
import com.kky.domain.model.Post

class PostListAdapter : PagingDataAdapter<Post, PostListAdapter.PostViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(
            ItemPostCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    class PostViewHolder(private val binding: ItemPostCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) = with(binding) {
            binding.data = item
        }
    }

    private class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.writer == newItem.writer && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }
}