package com.justso.learn.page

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.justso.learn.data.PageSource
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class PageRepository private constructor(private var context: Context) {
    fun getData(): List<MockData> {
        return emptyList()
    }

     fun getPageData(pageSize: Int) = Pager(PagingConfig(pageSize)) {
        object : PagingSource<Int, MockData>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MockData> {
                return try {
                    val data = getData()
                    LoadResult.Page(
                        data = data.map { it },
                        prevKey = null,
                        nextKey = null
                    )
                } catch (ex: Exception) {
                    LoadResult.Error(ex)
                }
            }
        }
    }.flow

    companion object {
        @Volatile
        private var instance: PageRepository? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: PageRepository(context = context).also { instance = it }
        }
    }
}