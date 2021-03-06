package com.waddress.myweather.model

import com.squareup.moshi.Json

/**
 * Created by z.HAGUI.
 */

data class CurrentObservation(@Json(name = "nowcast")
                              val nowcast: String = "",
                              @Json(name = "temp_c")
                              val tempC: Int = 0,
                              @Json(name = "observation_epoch")
                              val observationEpoch: String = "",
                              @Json(name = "temp_f")
                              val tempF: Int = 0,
                              @Json(name = "wind_kph")
                              val windKph: Int = 0,
                              @Json(name = "wind_mph")
                              val windMph: Int = 0,
                              @Json(name = "wind_degrees")
                              val windDegrees: Int = 0,
                              @Json(name = "temperature_string")
                              val temperatureString: String = "",
                              @Json(name = "weather")
                              val weather: String = "",
                              @Json(name = "feelslike_string")
                              val feelslikeString: String = "",
                              @Json(name = "precip_today_metric")
                              val precipTodayMetric: String = "",
                              @Json(name = "precip_1hr_string")
                              val precipHrString: String = "",
                              @Json(name = "icon_url")
                              val iconUrl: String = "",
                              @Json(name = "image")
                              val image: Image,
                              @Json(name = "UV")
                              val uv: String = "",
                              @Json(name = "station_id")
                              val stationId: String = "",
                              @Json(name = "local_epoch")
                              val localEpoch: String = "",
                              @Json(name = "local_tz_short")
                              val localTzShort: String = "",
                              @Json(name = "wind_dir")
                              val windDir: String = "",
                              @Json(name = "precip_1hr_metric")
                              val precipHrMetric: String = "",
                              @Json(name = "pressure_in")
                              val pressureIn: String = "",
                              @Json(name = "local_tz_long")
                              val localTzLong: String = "",
                              @Json(name = "wind_gust_mph")
                              val windGustMph: Int = 0,
                              @Json(name = "windchill_string")
                              val windchillString: String = "",
                              @Json(name = "wind_gust_kph")
                              val windGustKph: Int = 0,
                              @Json(name = "wind_string")
                              val windString: String = "",
                              @Json(name = "local_time_rfc822")
                              val localTimeRfc: String = "",
                              @Json(name = "visibility_km")
                              val visibilityKm: String = "",
                              @Json(name = "relative_humidity")
                              val relativeHumidity: String = "",
                              @Json(name = "pressure_mb")
                              val pressureMb: String = "",
                              @Json(name = "observation_time_rfc822")
                              val observationTimeRfc: String = "",
                              @Json(name = "precip_1hr_in")
                              val precipHrIn: String = "",
                              @Json(name = "feelslike_c")
                              val feelslikeC: String = "",
                              @Json(name = "observation_time")
                              val observationTime: String = "",
                              @Json(name = "feelslike_f")
                              val feelslikeF: String = "",
                              @Json(name = "history_url")
                              val historyUrl: String = "",
                              @Json(name = "windchill_f")
                              val windchillF: String = "",
                              @Json(name = "windchill_c")
                              val windchillC: String = "",
                              @Json(name = "precip_today_string")
                              val precipTodayString: String = "",
                              @Json(name = "icon")
                              val icon: String = "",
                              @Json(name = "precip_today_in")
                              val precipTodayIn: String = "",
                              @Json(name = "solarradiation")
                              val solarradiation: String = "",
                              @Json(name = "observation_location")
                              val observationLocation: ObservationLocation,
                              @Json(name = "dewpoint_f")
                              val dewpointF: Int = 0,
                              @Json(name = "display_location")
                              val displayLocation: DisplayLocation,
                              @Json(name = "dewpoint_string")
                              val dewpointString: String = "",
                              @Json(name = "pressure_trend")
                              val pressureTrend: String = "",
                              @Json(name = "dewpoint_c")
                              val dewpointC: Int = 0,
                              @Json(name = "estimated")
                              val estimated: Estimated,
                              @Json(name = "forecast_url")
                              val forecastUrl: String = "",
                              @Json(name = "local_tz_offset")
                              val localTzOffset: String = "",
                              @Json(name = "heat_index_f")
                              val heatIndexF: String = "",
                              @Json(name = "heat_index_c")
                              val heatIndexC: String = "",
                              @Json(name = "ob_url")
                              val obUrl: String = "",
                              @Json(name = "heat_index_string")
                              val heatIndexString: String = "",
                              @Json(name = "visibility_mi")
                              val visibilityMi: String = "")