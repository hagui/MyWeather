package com.waddress.myweather.dagger.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.waddress.myweather.dagger.ViewModelKey
import com.waddress.myweather.viewmodels.ViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Z.HAG
 */
@Module
abstract class ViewModelModule {


   /* @Binds
    @IntoMap
    @ViewModelKey(WinrakViewModel::class)
    internal abstract fun bindRestaurantsViewModel(restaurantsViewModel: WinrakViewModel): ViewModel*/

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}