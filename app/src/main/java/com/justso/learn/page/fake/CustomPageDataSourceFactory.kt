package com.justso.learn.page.fake

import androidx.paging.DataSource
import com.justso.learn.vo.RedditPost

class CustomPageDataSourceFactory(val repository: DataRepository): DataSource.Factory<Int, RedditPost>() {
    override fun create(): DataSource<Int, RedditPost> {
        return CustomPageDataSource(repository)
    }
}