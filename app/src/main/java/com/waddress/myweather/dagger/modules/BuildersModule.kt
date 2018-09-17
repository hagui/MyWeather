package com.waddress.myweather.dagger.modules

import com.waddress.myweather.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by z.hagui
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMapsActivity(): MainActivity
}