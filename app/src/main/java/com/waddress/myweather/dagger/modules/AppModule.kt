package com.waddress.myweather.dagger.modules

import android.app.Application
import android.content.Context
import com.waddress.myweather.utils.Utils

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created z.HAGUI.
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
    fun provideAndroidManager():AndroidModule = AndroidModule(app)



}