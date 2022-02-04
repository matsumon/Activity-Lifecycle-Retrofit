package com.example.android.connectedweather.data

data class forecast_all(
    var datetime: String,
    var wind: String,
    var cloud: String,
    var rain: String,
    var image: String,
    var highTemp: String,
    var lowTemp: String,
    var description: String
)
