package com.example.android.connectedweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.connectedweather.data.forecast
import com.google.android.material.snackbar.Snackbar
import java.util.*
import android.util.Log
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class ForecastAdapter(var forecastPeriods: List<forecast?>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    public fun updateForecast(newList: List<forecast?>){
        forecastPeriods = newList ?: listOf()
        notifyDataSetChanged()
    }
    override fun getItemCount() = this.forecastPeriods.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.forecastPeriods[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val monthTV: TextView = view.findViewById(R.id.tv_month)
        private val dayTV: TextView = view.findViewById(R.id.tv_day)
        private val highTempTV: TextView = view.findViewById(R.id.tv_high_temp)
        private val lowTempTV: TextView = view.findViewById(R.id.tv_low_temp)
//        private val shortDescTV: TextView = view.findViewById(R.id.tv_short_description)
        private val shortDescTV: String =""
        private val popTV: TextView = view.findViewById(R.id.tv_pop)
        private val image: ImageView = view.findViewById(R.id.image)
        private lateinit var currentForecastPeriod: forecast

        init {
            view.setOnClickListener {
                Snackbar.make(
                    view,
                    "Placeholder",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        fun bind(forecastPeriod: forecast?) {
            if (forecastPeriod == null) {
                    return
            }
            currentForecastPeriod = forecastPeriod

//            val cal = Calendar.getInstance()
//            cal.set(forecastPeriod.year, forecastPeriod.month, forecastPeriod.day)
//
//            monthTV.text = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())
//            dayTV.text = cal.get(Calendar.DAY_OF_MONTH).toString()
//            highTempTV.text = forecastPeriod.highTemp.toString() + "°F"
//            lowTempTV.text = forecastPeriod.lowTemp.toString() + "°F"
            monthTV.text = "AUG"
            dayTV.text = "10"
            highTempTV.text = "${forecastPeriod.main.highTemp}°F"
            lowTempTV.text = "${forecastPeriod.main.lowTemp} °F"

            Log.d("blue","HERE $forecastPeriod")
            var rain = forecastPeriod.rain

            rain = rain * 100

            popTV.text = "\uD83C\uDF02" + rain.toString() + "%"
//            shortDescTV.text = forecastPeriod.shortDesc
//            Picasso.get().setLoggingEnabled(true)
            Picasso.get().load("http://openweathermap.org/img/wn/10n@2x.png").into(image)
            val time = getDateTime(forecastPeriod.date.toString())
            Log.d("orange","${time?.monthDay}")
        }
//        COPIED Part of FUNCTION FROM https://www.codegrepper.com/code-examples/java/convert+timestamp+to+date+android to get time
         fun getDateTime(s: String): dateObject? {
                val Timestamp: Long = 1633304782
//                val Timestamp: Long = s.toLong()
                val timeD = Date(Timestamp * 1000)
                val sdf = SimpleDateFormat("HH:mm:ss")
                val Time = sdf.format(timeD)
                var orange:dateObject = dateObject(
                        monthDay="july 5",
                        time="12:123am"
                    )
                return orange
        }
        data class dateObject(
            var monthDay: String,
            var time: String,
        )
    }
}