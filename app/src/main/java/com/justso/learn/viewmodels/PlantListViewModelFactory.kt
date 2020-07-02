package com.justso.learn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justso.learn.data.PlantRepository

@Suppress("UNCHECKED_CAST")
class PlantListViewModelFactory(private val repository: PlantRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantListViewModel(repository = repository) as T
    }
}