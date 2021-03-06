package com.waddress.myweather.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.waddress.myweather.datasources.webservice.Resource
import com.waddress.myweather.model.City
import com.waddress.myweather.model.Conditions
import com.waddress.myweather.model.WeatherRepository
import javax.inject.Inject

/**
 * Created by z.HAGUI.
 */

class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    var initialized = false

    var weatherInput: MutableLiveData<City> = MutableLiveData()

    val weather: LiveData<Resource<Conditions>> = Transformations.switchMap(weatherInput) {

        initialized = true; repository.getWeather(it.countryIso,it.city)
    }

}