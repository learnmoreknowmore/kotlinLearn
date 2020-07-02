package com.justso.learn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justso.learn.data.GardenPlantingRepository

@Suppress("UNCHECKED_CAST")
class GardenPlantingViewModelFactory(private val gardenRepository:GardenPlantingRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GardenPlantingViewModel(gardenRepository) as T
    }
}