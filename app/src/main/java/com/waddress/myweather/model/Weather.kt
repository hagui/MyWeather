package com.waddress.myweather.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by z.HAGUI.
 */
@Entity (tableName = "weather")
data class Weather(@PrimaryKey (autoGenerate = true) val id: Int, val city: String = "",
                   val temperature: Float, val joined_date: String = "",
                   val image: String = "", val description: String = "") : Serializable