package com.waddress.myweather.datasources.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.waddress.myweather.BuildConfig
import com.waddress.myweather.datasources.webservice.Resource
import com.waddress.myweather.datasources.webservice.Error
import com.waddress.myweather.model.Conditions
import com.waddress.myweather.model.Weather
import com.waddress.myweather.utils.AppExecutors
import retrofit2.Call
import java.io.IOException

/**
 * Created by z.hagui
 */
abstract class NetworkBoundResource<T>(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<T>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDatabase()
       /* result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldLoadFromNetwork(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData -> result.setValue(Resource.success(newData)) }
            }
        }*/
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {

        appExecutors.networkIO().execute {

            try {

                val response = createNetworkCall().execute().body()
                println("response is: $response")
                //TODO check for success
                when (response != null) {

                    true -> appExecutors.diskIO().execute {
                        saveNetworkCallResult(response)
                        appExecutors.mainThread().execute {
                            val newDbSource = loadFromDatabase()
                          /*  result.addSource(newDbSource!!) { newData ->
                                result.removeSource(newDbSource)
                                result.setValue(Resource.success(response))
                            }*/
                            result.setValue(Resource.success(response))
                        }
                    }
                    false -> appExecutors.mainThread().execute {
                       // result.addSource(dbSource) { newData ->
                        result.setValue(Resource.error<T>(response, Error(500, "soucis d'enregistremennt dans la bd")))
                    }

                }
            } catch (exc: IOException) {
                System.err.println("Make sure your server ${BuildConfig.URL} is running.")
                appExecutors.mainThread().execute {
                   // result.addSource(dbSource) { newData ->
                    result.setValue(Resource.error(null, Error(503, "Service Unavailable.")))
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<T>> = result

    @WorkerThread
    abstract fun saveNetworkCallResult(data: T?)

    @MainThread
    protected abstract fun shouldLoadFromNetwork(data: T?): Boolean

    @MainThread
    abstract fun loadFromDatabase(): LiveData<List<Weather>>?

    @WorkerThread
    abstract fun createNetworkCall(): Call<T>
}