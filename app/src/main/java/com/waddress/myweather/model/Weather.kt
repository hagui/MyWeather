package com.waddress.myweather.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.time.OffsetDateTime


@Entity
data class Weather(@PrimaryKey val id: Int, val city: String = "",
                   val temperature: Float, val joined_date: OffsetDateTime? = null,
                   val image: String = "", val description: String = "") : Serializable