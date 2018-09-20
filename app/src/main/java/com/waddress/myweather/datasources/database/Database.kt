package com.waddress.myweather.datasources.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.waddress.myweather.model.Weather
import com.waddress.myweather.model.WeatherDao

/**
 * Created by z.HAGUI.
 */

@Database(entities = arrayOf(Weather::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}