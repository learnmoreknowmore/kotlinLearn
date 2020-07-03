package com.justso.learn.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.paging.PagingData
import com.justso.learn.data.NetworkRepository
import com.justso.learn.vo.RedditPost
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class NetworkViewModel internal constructor(private val repository:NetworkRepository,private val savedStateHandle: SavedStateHandle): ViewModel() {
    companion object {
        const val KEY_SUBREDDIT = "subreddit"
        const val DEFAULT_SUBREDDIT = "androiddev"
    }
    init {
        if (!savedStateHandle.contains(KEY_SUBREDDIT)) {
            savedStateHandle.set(KEY_SUBREDDIT, DEFAULT_SUBREDDIT)
        }
    }
    private val clearListCh = Channel<Unit>(Channel.CONFLATED)

    val data = flowOf(
            clearListCh.consumeAsFlow().map { PagingData.empty<RedditPost>() },
            savedStateHandle.getLiveData<String>(KEY_SUBREDDIT)
            .asFlow()
            .flatMapLatest { repository.getNetworkData(it,30) }

    ).flattenMerge(concurrency = 2)
}