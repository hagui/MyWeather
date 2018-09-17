package com.waddress.myweather.utils

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by z.hagui
 */
@Singleton
class Utils @Inject constructor(private val context: Context) {

    fun hasConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isAvailable && activeNetwork.isConnected
    }


    fun fahrenheitToCelsius(temp:Float): Float {

        return ((temp - 32)*5)/9
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getBitmap(drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        return when (drawable) {
            is BitmapDrawable -> BitmapFactory.decodeResource(context.resources, drawableId)
            is VectorDrawable -> getBitmap(drawable)
            else -> throw IllegalArgumentException("unsupported drawable type")
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getBitmap(vectorDrawable: VectorDrawable): Bitmap {
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

}