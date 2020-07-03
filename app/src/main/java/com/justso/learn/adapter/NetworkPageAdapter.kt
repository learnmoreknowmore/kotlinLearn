package com.justso.learn.adapter

import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.justso.learn.R
import com.justso.learn.data.MockData
import com.justso.learn.databinding.ItemNetworkDataBinding
import com.justso.learn.databinding.ListItemGardenPlantingBinding
import com.justso.learn.vo.RedditPost

class NetworkPageAdapter: PagingDataAdapter<RedditPost, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NetworkViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_network_data,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NetworkViewHolder).bind(getItem(position))
    }
    class NetworkViewHolder(val binding: ItemNetworkDataBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:RedditPost?){
            binding.item = item
            with(binding){
                executePendingBindings()
            }
        }

    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditPost>(){
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem == newItem
            }
        }
    }
}