package com.justso.learn.fake

import com.justso.learn.vo.RedditPost


class DataRepository {

    private val data = ArrayList<RedditPost>()

    init {
        for (i in 0..100) {
            val bean = RedditPost("name $i")
            data.add(bean)
        }

    }

    fun loadData(size: Int): List<RedditPost> {
        return data.subList(0, size)
    }

    fun loadData(index: Int, size: Int): List<RedditPost>? {

        if (index >= data.size - 1 || index < 1) {
            return null
        }

        if (index + size > data.size) {
            return data.subList(index + 1, data.size)
        }
        return data.subList(index + 1, index + size)
    }

    fun loadPageData(page: Int, size: Int): List<RedditPost>? {

        val totalPage = if (data.size % size == 0) {
            data.size / size
        } else {
            data.size / size + 1
        }

        if (page > totalPage || page < 1) {
            return null
        }

        if (page == totalPage) {
            return data.subList((page - 1) * size, data.size)
        }
        return data.subList((page - 1) * size, page * size)
    }
}