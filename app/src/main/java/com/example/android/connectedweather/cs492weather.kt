package com.example.android.connectedweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso

class cs492weather : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs492weather)
        var image = findViewById<ImageView>(R.id.image)
        Picasso.get().load("http://openweathermap.org/img/wn/10n@2x.png").resize(500,500).into(image)
    }
}