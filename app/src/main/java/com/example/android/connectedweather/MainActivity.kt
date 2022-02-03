package com.example.android.connectedweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import android.util.Log
import com.example.android.connectedweather.data.forecast
import com.example.android.connectedweather.data.overview
import com.squareup.moshi.Moshi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {
    private var forecastDataItems: List<forecast?> = listOf()
    private lateinit var forecastListRV: RecyclerView
    private lateinit var requestQueue: RequestQueue
    private var moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val jsonAdapter: JsonAdapter<overview> =
            moshi.adapter(overview::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)
        volleyRequest()
        forecastListRV = findViewById<RecyclerView>(R.id.rv_forecast_list)
        forecastListRV.layoutManager = LinearLayoutManager(this)
        forecastListRV.adapter = ForecastAdapter(forecastDataItems)
    }

    fun volleyRequest(): Unit{
        val url = "https://api.openweathermap.org/data/2.5/forecast?lat=44.5646&lon=-123.2620&appid=97a47cb2c6f46038cde0645cbf6d4224&units=imperial"
//        val url = "https://api.openweathermap.org/data/2.5/forecast?lat=31&lon=-100&appid=97a47cb2c6f46038cde0645cbf6d4224&units=imperial"
        val req = StringRequest(
            Request.Method.GET,
            url,
            {
                val results = jsonAdapter.fromJson(it)
                Log.d("orange","SUCCESS HERE $results")
                val date = results?.list?.elementAt(0)?.date
                Log.d("orange","$date")
                forecastListRV.adapter = ForecastAdapter((results?.list ?: listOf()))

            },
            {
                Log.d("orange","ERROR HERE $it")
            }
        )
        requestQueue.add(req)
    }

}