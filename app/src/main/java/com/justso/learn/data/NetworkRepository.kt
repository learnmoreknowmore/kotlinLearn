package com.justso.learn.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.justso.learn.api.NetworkApi


class NetworkRepository {
    fun getNetworkData(reddit: String, pageSize: Int) = Pager(PagingConfig(pageSize)){
        val api:NetworkApi = NetworkApi.create()
        PageSource(api,reddit)
    }.flow

    enum class Type {
        IN_MEMORY_BY_ITEM,
        IN_MEMORY_BY_PAGE,
        DB
    }
    companion object{
        @Volatile
        private var instance:NetworkRepository? = null
        fun getInstance() = instance?: synchronized(this){
            instance?:NetworkRepository().also { instance = it  }
        }
    }
}