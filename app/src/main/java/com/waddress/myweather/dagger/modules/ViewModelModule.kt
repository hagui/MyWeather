package com.waddress.myweather.dagger.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.waddress.myweather.dagger.ViewModelKey
import com.waddress.myweather.viewmodels.AutoCompleteViewModel
import com.waddress.myweather.viewmodels.ViewModelFactory
import com.waddress.myweather.viewmodels.WeatherViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by z.HAGUI.
 */
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun bindRestaurantsViewModel(restaurantsViewModel: WeatherViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AutoCompleteViewModel::class)
    internal abstract fun bindAutoCompeletViewModel(autoCompleteViewModel: AutoCompleteViewModel): ViewModel

}