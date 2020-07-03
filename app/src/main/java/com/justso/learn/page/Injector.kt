package com.justso.learn.page

import android.content.Context

object Injector {
    private fun getPageRepository(context: Context):PageRepository{
        return PageRepository.getInstance(context)
    }
    fun providePageViewModelFactory(context: Context):PageViewModelFactory{
        val repository:PageRepository = getPageRepository(context)
        return PageViewModelFactory(repository = repository)
    }
}