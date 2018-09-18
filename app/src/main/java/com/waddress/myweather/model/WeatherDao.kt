package com.waddress.myweather.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun queryRestaurants(): LiveData<List<Weather>>

    // select weather selon la ville
    @Query("SELECT * FROM weather WHERE city LIKE :city")
    fun queryWeatherByCity(city: String): LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(weather: Weather)
}