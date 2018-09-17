package com.waddress.myweather.view

import android.support.v7.app.AppCompatActivity
import com.waddress.myweather.dagger.WeatherApplication

/**
 * Created z.hagui
 */
abstract class AbstractActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = WeatherApplication.refWatcher(this)
        refWatcher.watch(this)
    }
}