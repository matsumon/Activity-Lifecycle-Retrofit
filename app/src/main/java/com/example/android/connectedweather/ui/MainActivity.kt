package com.example.android.connectedweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.example.android.connectedweather.data.forecast
import com.example.android.connectedweather.data.forecast_all
import com.google.android.material.progressindicator.CircularProgressIndicator
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.view.View
import com.example.android.connectedweather.R
import androidx.activity.viewModels
import com.example.android.connectedweather.data.LoadingStatus

class MainActivity : AppCompatActivity() {
    private var forecastDataItems: List<forecast?> = listOf()
    private lateinit var forecastListRV: RecyclerView
    private val viewModel: WeatherViewModel by viewModels()
    private val forecastAdapter = ForecastAdapter(forecastDataItems,::activityHandler)

    lateinit var errorState: TextView
    lateinit var loading: CircularProgressIndicator
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("blue", "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        errorState=findViewById(R.id.tv_search_error)
        loading=findViewById(R.id.loading_indicator)
        forecastListRV = findViewById<RecyclerView>(R.id.rv_forecast_list)
        forecastListRV.layoutManager = LinearLayoutManager(this)
        forecastListRV.adapter = forecastAdapter
        viewModel.loadForecastResults("Corvallis,OR,US")
        viewModel.forecastResults.observe(this) { forecastResults ->
            Log.d("Blue","OBSERVER TRIGGERED")
            forecastAdapter.updateForecast(forecastResults?.list)
        }
        viewModel.loadingStatus.observe(this) { loadingStatus ->
            when (loadingStatus) {
                LoadingStatus.LOADING -> {
                    loading.visibility = View.VISIBLE
                    forecastListRV.visibility = View.INVISIBLE
                    errorState.visibility = View.INVISIBLE
                }
                LoadingStatus.ERROR -> {
                    loading.visibility = View.INVISIBLE
                    forecastListRV.visibility = View.INVISIBLE
                    errorState.visibility = View.VISIBLE
                }
                else -> {
                    loading.visibility = View.INVISIBLE
                    forecastListRV.visibility = View.VISIBLE
                    errorState.visibility = View.INVISIBLE
                }
            }
        }
    }
    override fun onResume() {
        Log.d("blue", "onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("blue", "onPause()")
        super.onPause()
    }
    fun activityHandler(item: forecast_all){
        val intent = Intent(this, cs492weather::class.java)
        intent.putExtra("datetime",item.datetime)
        intent.putExtra("wind",item.wind)
        intent.putExtra("cloud",item.cloud)
        intent.putExtra("rain",item.rain)
        intent.putExtra("image",item.image)
        intent.putExtra("highTemp",item.highTemp)
        intent.putExtra("lowTemp",item.lowTemp)
        intent.putExtra("description",item.description)

        startActivity(intent)
    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.map ->{
//                Log.d("blue","MAP")
                val uri = Uri.parse("geo:44.5646,-123.2620")
                val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}