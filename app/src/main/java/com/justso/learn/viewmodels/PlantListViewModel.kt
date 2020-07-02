package com.justso.learn.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.justso.learn.data.PlantRepository

class PlantListViewModel internal constructor(repository: PlantRepository): ViewModel() {
    private val growZoneNumber = MutableLiveData<Int>(NO_GROW_ZONE)

    val plants = growZoneNumber.switchMap {
        if (it == NO_GROW_ZONE){
            repository.getPlants()
        }else{
            repository.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
    }
}