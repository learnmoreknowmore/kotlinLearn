package com.justso.learn.page

import androidx.lifecycle.ViewModel
import com.justso.learn.data.NetworkRepository

class PageViewModel(private val repository: PageRepository):ViewModel() {
    val data = repository.getPageData(30)
}