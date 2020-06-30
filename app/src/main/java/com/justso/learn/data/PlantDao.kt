package com.justso.learn.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlantDao {
    /**
     * 查询所有
     */
    @Query("SELECT * FROM plants ORDER BY name")
    fun getPlants()
    /**
     * 获取某个
     */
    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlant(plantId:String)

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): LiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants:List<Plant>)
}