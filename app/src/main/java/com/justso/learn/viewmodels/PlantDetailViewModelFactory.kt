package com.justso.learn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.justso.learn.data.GardenPlantingRepository
import com.justso.learn.data.PlantRepository

@Suppress("UNCHECKED_CAST")
class PlantDetailViewModelFactory constructor(private val plantRepository: PlantRepository,
                                              private val gardenPlantingRepository: GardenPlantingRepository,
                                              private val plantId: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository,gardenPlantingRepository,plantId) as T
    }
}