package com.justso.learn.utils

import android.content.Context
import com.justso.learn.data.AppDatabase
import com.justso.learn.data.GardenPlantingRepository
import com.justso.learn.data.PlantRepository
import com.justso.learn.viewmodels.GardenPlantingViewModelFactory
import com.justso.learn.viewmodels.PlantDetailViewModelFactory
import com.justso.learn.viewmodels.PlantListViewModel
import com.justso.learn.viewmodels.PlantListViewModelFactory

object InjectorUtils {
    private fun getPlantRepository(context: Context):PlantRepository{
        return PlantRepository.getInstance(AppDatabase.getInstance(context.applicationContext).plantDao())
    }
    private fun getGardenPlantingRepository(context: Context):GardenPlantingRepository{
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context.applicationContext).gardenPlantingDao())
    }

    fun providePlantListViewModelFactory(context: Context):PlantListViewModelFactory{
        val repository:PlantRepository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }
    fun provideGardenPlantingViewModelFactory(context: Context):GardenPlantingViewModelFactory{
        val repository:GardenPlantingRepository = getGardenPlantingRepository(context)
        return GardenPlantingViewModelFactory(repository)
    }
    fun providePlantDetailViewModelFactory(context: Context,plantId:String):PlantDetailViewModelFactory{
        return PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context),plantId)
    }
}