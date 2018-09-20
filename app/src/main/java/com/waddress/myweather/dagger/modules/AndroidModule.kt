package com.waddress.myweather.dagger.modules


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.SharedPreferences
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by z.HAGUI.
 */

@Module
class AndroidModule @Inject constructor(private val context: Context) {


    @Provides
    @Singleton
    fun provideLocationManager(): LocationManager {
        return context.getSystemService(LOCATION_SERVICE) as LocationManager
    }


    @Provides
    @Singleton
    fun providesSharedPreferences():
            SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun locationEnabled(): Boolean {
        val service: LocationManager = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        val enabled: Boolean = service.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return when {
            !enabled -> {
                false
            }
            else -> true
        }
    }

    @Provides
    @Singleton
    fun getLocationProvider():FusedLocationProviderClient{
      return LocationServices.getFusedLocationProviderClient(context)
    }


    @SuppressLint("MissingPermission")
    @Provides
    @Singleton
    fun getLocalLocation(): String {
        val fusedLocationClient: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)
        var result = "location fail"

        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    Log.d("location", location.toString())
                    result = location.toString()
                }
        return result
    }


    @SuppressLint("MissingPermission")
    @Provides
    @Singleton
    fun getGeocoder(): Geocoder {
        val geocoder: Geocoder = Geocoder(context, Locale.getDefault())
        return geocoder
    }







    @SuppressLint("MissingPermission")
    @Provides
    @Singleton
    fun getLastLocation():String {
        var result = "location fail"
        // Get last known recent location using new Google Play Services SDK (v11+)
        val locationClient = getFusedLocationProviderClient(context)

        locationClient.lastLocation
                .addOnSuccessListener { location ->
                    // GPS location can be null if GPS is switched off
                    if (location != null) {
                        result = location.toString()
                    }
                }
                .addOnFailureListener { e ->
                    Log.d("AndroidModule", "Error trying to get last GPS location")
                    e.printStackTrace()
                    result = e.message.toString()
                }

                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful && task.result != null) {
                        task.result

                    } else {
                        Log.w("exception", "getLastLocation:exception", task.exception)

                    }
                }

        return result
    }


}