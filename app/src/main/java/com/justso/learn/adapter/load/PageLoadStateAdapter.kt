package com.justso.learn.adapter.load

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.work.ListenableWorker.Result.retry
import com.justso.learn.adapter.NetworkPageAdapter

class PageLoadStateAdapter(var adapter:NetworkPageAdapter): LoadStateAdapter<NetworkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent){
            retry()
        }
    }
}