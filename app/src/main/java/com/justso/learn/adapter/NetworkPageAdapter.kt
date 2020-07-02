package com.justso.learn.adapter

import android.database.DatabaseUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.justso.learn.R
import com.justso.learn.data.MockData
import com.justso.learn.databinding.ItemNetworkDataBinding
import com.justso.learn.databinding.ListItemGardenPlantingBinding

class NetworkPageAdapter: PagedListAdapter<MockData, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NetworkViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_network_data,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NetworkViewHolder).bind(getItem(position))
    }
    class NetworkViewHolder(val binding: ItemNetworkDataBinding):RecyclerView.ViewHolder(binding.root){
        init {

        }
        fun bind(item:MockData?){
            with(binding){
                executePendingBindings()
            }
        }

    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MockData>(){
            override fun areItemsTheSame(oldItem: MockData, newItem: MockData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MockData, newItem: MockData): Boolean {
                return oldItem == newItem
            }
        }
    }
}