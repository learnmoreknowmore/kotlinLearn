package com.justso.learn.data

class GardenPlantingRepository private constructor(private val gardenPlantingDao: GardenPlantingDao){
    /**
     *
     */
    fun getGardenPlantings() = gardenPlantingDao.getGardenPlantings()
    /**
     *
     */
    fun isPlanted(plantId:String) = gardenPlantingDao.isPlanted(plantId)
    /**
     *
     */
    fun getPlantedGardens() = gardenPlantingDao.getPlantedGardens()
    /**
     *
     */
    suspend fun createGardenPlanting(plantId: String){
        val gardenPlanting:GardenPlanting = GardenPlanting(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }
    /**
     *
     */
    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting){
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
    }

    companion object{
        @Volatile
        private var instance:GardenPlantingRepository? = null
        fun getInstance(gardenPlantingDao: GardenPlantingDao) = instance?: synchronized(this){
            instance?:GardenPlantingRepository(gardenPlantingDao).also { instance = it }
        }
    }
}