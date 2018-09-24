package com.waddress.myweather.viewmodels

import android.arch.lifecycle.*
import com.waddress.myweather.datasources.webservice.Resource
import com.waddress.myweather.model.City
import com.waddress.myweather.model.Conditions
import com.waddress.myweather.model.WeatherRepository
import com.waddress.myweather.model.autocomplete.AutoCompleteRepository
import com.waddress.myweather.model.autocomplete.Response
import javax.inject.Inject

/**
 * Created by z.HAGUI.
 */

class AutoCompleteViewModel @Inject constructor(private val repository: AutoCompleteRepository) : ViewModel() {

    var initialized = false

    var urlInput: MutableLiveData<String> = MutableLiveData()

    val weather: LiveData<Resource<Response>> = Transformations.switchMap(urlInput) {

        initialized = true; repository.getCountryList(it)
    }

}