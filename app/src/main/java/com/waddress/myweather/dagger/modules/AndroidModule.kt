package com.waddress.myweather.dagger.modules

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.SharedPreferences
import android.location.LocationManager
import android.preference.PreferenceManager
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AndroidModule @Inject constructor(private val context: Context) {


    @Provides
    @Singleton
    fun provideLocationManager(): LocationManager {
        return context.getSystemService(LOCATION_SERVICE) as LocationManager
    }


    @Provides
    @Singleton
    fun providesSharedPreferences():
            SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }


}