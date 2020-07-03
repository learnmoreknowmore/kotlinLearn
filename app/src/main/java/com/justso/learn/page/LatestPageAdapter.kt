package com.justso.learn.page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.justso.learn.databinding.ItemPageContentBinding

class LatestPageAdapter: PagingDataAdapter<MockData, LatestPageAdapter.LatestPageViewHolder>(DIFF_CALL) {
    override fun onBindViewHolder(holder: LatestPageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestPageViewHolder {
        //return LatestPageViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_page_content,parent,false))
        return LatestPageViewHolder(ItemPageContentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    class LatestPageViewHolder(private val binding: ItemPageContentBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:MockData?){
            with(binding){
                binding.item = item
                executePendingBindings()
            }
        }
    }
    companion object{
        val DIFF_CALL = object :DiffUtil.ItemCallback<MockData>(){
            override fun areItemsTheSame(oldItem: MockData, newItem: MockData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MockData, newItem: MockData): Boolean {
                return oldItem == newItem
            }
        }
    }
}