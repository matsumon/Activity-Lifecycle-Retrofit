package com.example.android.connectedweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.connectedweather.data.forecast
import com.example.android.connectedweather.data.forecast_all
import com.google.android.material.snackbar.Snackbar
import java.util.*
import android.util.Log
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class ForecastAdapter(var forecastPeriods: List<forecast?>, var activityHandler: (forecast_all) -> Unit) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    public fun updateForecast(newList: List<forecast?>){
        forecastPeriods = newList ?: listOf()
        notifyDataSetChanged()
    }
    override fun getItemCount() = this.forecastPeriods.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view,activityHandler)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.forecastPeriods[position])
    }

    class ViewHolder(view: View, public var activityHandler: (forecast_all)-> Unit) : RecyclerView.ViewHolder(view) {
        private val monthTV: TextView = view.findViewById(R.id.tv_month)
        private val dayTV: TextView = view.findViewById(R.id.tv_day)
        private val highTempTV: TextView = view.findViewById(R.id.tv_high_temp)
        private val lowTempTV: TextView = view.findViewById(R.id.tv_low_temp)
        private val shortDescTV: String =""
        private val popTV: TextView = view.findViewById(R.id.tv_pop)
        private val image: ImageView = view.findViewById(R.id.image)
        private lateinit var currentForecastPeriod: forecast

        //        Copied portion of the code from https://stackoverflow.com/questions/47250263/kotlin-convert-timestamp-to-datetime
        fun getDateTime(s: String): dateObject? {
            val sdf = SimpleDateFormat("MMM d")
            val time = SimpleDateFormat("h:mm a")
            val netDate = Date(s.toLong() * 1000)
            val monthDay = sdf.format(netDate)
            val Time = time.format(netDate)
            var orange:dateObject = dateObject(
                monthDay=monthDay,
                time=Time
            )
            return orange
        }
        data class dateObject(
            var monthDay: String,
            var time: String,
        )
        init {
            view.setOnClickListener {
                val time = getDateTime(currentForecastPeriod.date.toString())
                var rain = currentForecastPeriod.rain
                rain = rain * 100
                var rainText = rain.toString() + "%"
                var cloudText =  "${currentForecastPeriod.clouds.all}" + "%"
                Log.d("blue",cloudText)
                var temp_forecast = forecast_all(
                    datetime= "${time?.monthDay}, ${time?.time}",
                    wind= "\uD83D\uDCA8${currentForecastPeriod.wind.speed}mph⛛",
                    cloud= cloudText,
                    rain= rainText,
                    image= "http://openweathermap.org/img/wn/${currentForecastPeriod.weather[0].icon}@2x.png",
                    highTemp= "\uD83E\uDD75${currentForecastPeriod.main.highTemp}°F",
                    lowTemp= "\uD83E\uDD76${currentForecastPeriod.main.lowTemp}°F",
                    description= currentForecastPeriod.weather[0].description
                )
                activityHandler(temp_forecast)
            }
        }
        fun bind(forecastPeriod: forecast?) {
            if (forecastPeriod == null) {
                    return
            }
            currentForecastPeriod = forecastPeriod

            val time = getDateTime(forecastPeriod.date.toString())
            monthTV.text = time?.monthDay
            dayTV.text = time?.time
            highTempTV.text = "${forecastPeriod.main.highTemp}°F"
            lowTempTV.text = "${forecastPeriod.main.lowTemp} °F"

            Log.d("blue","HERE $forecastPeriod")
            var rain = forecastPeriod.rain
            rain = rain * 100
            popTV.text = rain.toString() + "%"

//            Picasso.get().setLoggingEnabled(true)
            Picasso.get().load("http://openweathermap.org/img/wn/${forecastPeriod.weather[0].icon}@2x.png").into(image)

            Log.d("orange","MONTHDAY ${time?.monthDay}")
            Log.d("orange","TIME ${time?.time}")
        }
//        Copied portion of the code from https://stackoverflow.com/questions/47250263/kotlin-convert-timestamp-to-datetime
//         fun getDateTime(s: String): dateObject? {
//             val sdf = SimpleDateFormat("MMM d")
//             val time = SimpleDateFormat("h:mm a")
//             val netDate = Date(s.toLong() * 1000)
//             val monthDay = sdf.format(netDate)
//             val Time = time.format(netDate)
//                var orange:dateObject = dateObject(
//                        monthDay=monthDay,
//                        time=Time
//                    )
//                return orange
//        }
//        data class dateObject(
//            var monthDay: String,
//            var time: String,
//        )
    }
}