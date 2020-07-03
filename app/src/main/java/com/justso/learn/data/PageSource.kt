package com.justso.learn.data

import androidx.paging.PagingSource
import com.justso.learn.api.NetworkApi
import com.justso.learn.vo.RedditPost
import retrofit2.HttpException
import java.io.IOException

class PageSource
    (private val api:NetworkApi,private val title:String): PagingSource<String, RedditPost>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val data:NetworkApi.ListingData = api.getTop(title,
                after = if (params is LoadParams.Append)params.key else null,
                before = if (params is LoadParams.Append)params.key else null,
                limit = params.loadSize
                ).data
            LoadResult.Page(
                data = data.children.map { it.data },
                prevKey = data.before,
                nextKey = data.after
            )
        }catch(io:IOException){
            LoadResult.Error(io)
        }catch (http:HttpException){
            LoadResult.Error(http)
        }
    }
}