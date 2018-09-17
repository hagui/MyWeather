package com.waddress.myweather.dagger.components


import com.waddress.myweather.dagger.WeatherApplication
import com.waddress.myweather.dagger.modules.AppModule
import com.waddress.myweather.dagger.modules.BuildersModule
import com.waddress.myweather.dagger.modules.NetModule
import com.waddress.myweather.dagger.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, ViewModelModule::class, AppModule::class, NetModule::class))
interface AppComponent {
    fun inject(app: WeatherApplication)
}