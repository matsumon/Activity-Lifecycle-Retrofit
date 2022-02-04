package com.example.android.connectedweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class cs492weather : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs492weather)

        var datetime=intent.getStringExtra("datetime")
        var wind=intent.getStringExtra("wind")
        var cloud= intent.getStringExtra("cloud")
        var rain=intent.getStringExtra("rain")
        var icon= intent.getStringExtra("image")
        var highTemp= intent.getStringExtra("highTemp")
        var lowTemp= intent.getStringExtra("lowTemp")
        var description=intent.getStringExtra("description")

        var image = findViewById<ImageView>(R.id.image)
        Picasso.get().load(icon).resize(500,500).into(image)
        var windA = findViewById<TextView>(R.id.wind)
        var cloudA = findViewById<TextView>(R.id.cloud)
        var rainA = findViewById<TextView>(R.id.rain)
        var highTempA = findViewById<TextView>(R.id.high_temp)
        var lowTempA = findViewById<TextView>(R.id.low_temp)
        var descriptionA = findViewById<TextView>(R.id.description)
        var datetimeA = findViewById<TextView>(R.id.datetime)
        windA.text = wind
        rainA.text = rain
        highTempA.text = highTemp
        lowTempA.text = lowTemp
        descriptionA.text = description
        datetimeA.text = datetime
        cloudA.text = cloud
    }
}