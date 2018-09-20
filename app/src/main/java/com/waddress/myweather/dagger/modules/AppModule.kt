package com.waddress.myweather.dagger.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.waddress.myweather.datasources.database.Database
import com.waddress.myweather.model.WeatherDao
import com.waddress.myweather.utils.Utils

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by z.HAGUI.
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesUtils(): Utils = Utils(app)

    @Provides
    @Singleton
    fun provideAndroidManager(): AndroidModule = AndroidModule(app)


    @Provides
    @Singleton
    fun provideWeatherDatabase(app: Application): Database =
            Room.databaseBuilder(app, Database::class.java, "weather_db").build()

    @Provides
    @Singleton
    fun provideWeatherDao(db: Database): WeatherDao = db.weatherDao()
}
