package com.waddress.myweather.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.time.OffsetDateTime


@Entity (tableName = "weather")
data class Weather(@PrimaryKey (autoGenerate = true) val id: Int, val city: String = "",
                   val temperature: Float, val joined_date: OffsetDateTime? = null,
                   val image: String = "", val description: String = "") : Serializable