package com.justso.learn.viewmodels

import androidx.lifecycle.ViewModel
import com.justso.learn.data.GardenPlantingRepository

class GardenPlantingViewModel internal constructor(private val repository: GardenPlantingRepository):
    ViewModel() {
    val plantAndGardenPlantings = repository.getPlantedGardens()
}