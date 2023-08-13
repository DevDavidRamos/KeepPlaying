package com.example.keepplaying.presentation.home_menu.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.keepplaying.databinding.ItemRecentSearchBinding
import com.example.keepplaying.firebase.domain.model.RecentSearch



class RecentSearchListAdapter: ListAdapter<RecentSearch, RecentSearchListAdapter.RecentSearchViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<RecentSearch>() {
        override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
            return oldItem == newItem
        }
    }

    inner class RecentSearchViewHolder(val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        return RecentSearchViewHolder(
            ItemRecentSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val recentSearch = currentList[position]

        holder.binding.tvRecentSearch.text = recentSearch.query

        holder.binding.clRecentSearchParent.setOnClickListener {
            onRecentSearchClickListener?.let { click ->
                click(recentSearch)
            }
        }
    }

    protected var onRecentSearchClickListener : ((RecentSearch) -> Unit)? = null

    fun setRecentSearchClickListener(listener: (RecentSearch) -> Unit){
        onRecentSearchClickListener = listener
    }
}