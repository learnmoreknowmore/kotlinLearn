package com.justso.learn.fake

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.justso.learn.R
import com.justso.learn.databinding.ItemNetworkDataBinding
import com.justso.learn.vo.RedditPost

class PageAdapter: PagedListAdapter<RedditPost, PageAdapter.PageViewHolder>(DIFF_CALL) {
    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_network_data,parent,false))
    }
    class PageViewHolder(val binding:ItemNetworkDataBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:RedditPost?){
            with(binding){
                binding.item = item
                executePendingBindings()
            }
        }
    }
    companion object{
        val DIFF_CALL = object : DiffUtil.ItemCallback<RedditPost>(){
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem == newItem
            }
        }
    }
}