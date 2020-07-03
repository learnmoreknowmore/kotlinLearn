package com.justso.learn.page.fake

import androidx.paging.PageKeyedDataSource
import com.justso.learn.vo.RedditPost

class CustomPageDataSource(private val repository: DataRepository): PageKeyedDataSource<Int, RedditPost>() {
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RedditPost>) {
        val data = repository.loadPageData(params.key,params.requestedLoadSize)
        data?.let {
            callback.onResult(data, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RedditPost>) {
        val data = repository.loadPageData(params.key,params.requestedLoadSize)
        data?.let {
            callback.onResult(data, params.key - 1)
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RedditPost>
    ) {
        val data = repository.loadData(params.requestedLoadSize)
        callback.onResult(data, null, 2)
    }
}