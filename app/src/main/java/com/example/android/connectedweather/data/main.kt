package com.example.android.connectedweather.data
import com.squareup.moshi.Json
data class main(
    @Json(name="temp_min") var highTemp: String,
    @Json(name="temp_max") var lowTemp: String
)
