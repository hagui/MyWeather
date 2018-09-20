package com.waddress.myweather.view

import android.support.v7.app.AppCompatActivity
import com.waddress.myweather.dagger.WeatherApplication

/**
 * Created by z.HAGUI.
 */
abstract class AbstractActivity : AppCompatActivity() {

    // pour la gestion de LeakCanary

    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = WeatherApplication.refWatcher(this)
        refWatcher.watch(this)
    }
}