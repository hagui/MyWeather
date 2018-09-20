package com.waddress.myweather.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun queryweather(): LiveData<List<Weather>>

    // select weather using city name
    @Query("SELECT * FROM weather WHERE city LIKE :city")
    fun queryWeatherByCity(city: String): LiveData<List<Weather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCondition(weather: Weather)
}