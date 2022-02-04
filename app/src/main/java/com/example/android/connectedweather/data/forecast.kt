package com.example.android.connectedweather.data
import com.example.android.connectedweather.MainActivity
import com.squareup.moshi.Json

data class forecast(
    @Json(name="dt") var date: Long,
    var main: main,
    var weather: List<weather>,
    @Json(name="pop") var rain: Float,
    var clouds: clouds,
    var wind: wind
)
