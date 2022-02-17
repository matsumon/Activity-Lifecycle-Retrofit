package com.example.android.connectedweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.content.Intent
import com.example.android.connectedweather.R

class cs492weather : AppCompatActivity() {
    var wind: String? =""
    var datetime: String? =""
    var cloud: String? =""
    var rain: String? =""
    var highTemp: String? =""
    var lowTemp: String? =""
    var description: String? =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cs492weather)

         datetime=intent.getStringExtra("datetime")
         wind=intent.getStringExtra("wind")
         cloud= intent.getStringExtra("cloud")
         rain=intent.getStringExtra("rain")
        var icon= intent.getStringExtra("image")
        highTemp=intent.getStringExtra("highTemp")
         lowTemp= intent.getStringExtra("lowTemp")
         description=intent.getStringExtra("description")

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

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.share ->{
//                Log.d("blue","SHARE")
                var msg = """
                    $datetime has $rain of rain with temps between$lowTemp and $highTemp with a cloud cover of $cloud and winds of $wind
                    $description
                """.trimIndent()
                val sendIntent: Intent = Intent().apply{
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT, msg)
                    type="text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent,null)
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}