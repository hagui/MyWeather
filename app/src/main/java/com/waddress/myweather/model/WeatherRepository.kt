package com.waddress.myweather.model

import android.arch.lifecycle.LiveData
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import com.waddress.myweather.datasources.database.NetworkBoundResource
import com.waddress.myweather.datasources.webservice.Resource
import com.waddress.myweather.datasources.webservice.WebService
import com.waddress.myweather.utils.AppExecutors
import com.waddress.myweather.utils.TimeTypeConverter
import com.waddress.myweather.utils.Utils
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Z.hagui
 */
@Singleton
open class WeatherRepository @Inject constructor(val webService: WebService, val weatherDao: WeatherDao,
                                                 val utils: Utils, val appExecutors: AppExecutors) {

    val TAG = "Repository"

    open fun getWeather(contryCode: String, cityName: String): LiveData<Resource<Conditions>> {
        return object : NetworkBoundResource<Conditions>(appExecutors) {

            /**
             * insert search result in DB
             * require Android version >=o because of date format
             */
            @RequiresApi(Build.VERSION_CODES.O)
            override fun saveNetworkCallResult(data: Conditions?) {
                if (data != null) {
                    Log.d(TAG , "insertion dans la bd")
                    val weather: Weather = Weather(0,
                            data.currentObservation.displayLocation.city,
                            data.currentObservation.tempF.toFloat(),
                            data.currentObservation.localTimeRfc,
                            data.currentObservation.iconUrl,
                            data.currentObservation.icon)

                    weatherDao.insertCondition(weather)

                }
            }

            override fun shouldLoadFromNetwork(data: Conditions?): Boolean {
                Log.d(TAG , "vérification de connexion")
                return utils.hasConnection()
            }

            /**
             * search saved value if we arenot connected
             */
            override fun loadFromDatabase(): LiveData<List<Weather>>? {
                Log.d(TAG , "recherche dans l'historique")
              if(!utils.hasConnection()){
                  return  weatherDao.queryweather()
              } else {
                  return null
              }

            }
            /**
             * search temperature using City parameter
             */
            override fun createNetworkCall(): Call<Conditions> {
                return webService.getWeatherByName( contryCode, cityName)
            }

        }.asLiveData()
    }

}

