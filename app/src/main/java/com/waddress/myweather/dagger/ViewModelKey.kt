package com.waddress.myweather.dagger

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Z.HAGUI
 */
@MapKey
annotation
class ViewModelKey(val value: KClass<out ViewModel>)