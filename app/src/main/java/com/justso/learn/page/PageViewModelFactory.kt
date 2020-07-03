package com.justso.learn.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justso.learn.data.NetworkRepository

@Suppress("UNCHECKED_CAST")
class PageViewModelFactory(private val repository: PageRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PageViewModel(repository) as T
    }
}