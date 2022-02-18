package com.example.android.connectedweather.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.connectedweather.data.WeatherDataRequest
import com.example.android.connectedweather.data.WeatherInterface
import com.example.android.connectedweather.data.overview

import kotlinx.coroutines.launch

import com.example.android.connectedweather.data.LoadingStatus

//copied and modified from Rob Hess: https://github.com/osu-cs492-w22/LifecycleAwareGitHubSearch/blob/main/app/src/main/java/com/example/android/lifecyclegithubsearch/ui/GitHubSearchViewModel.kt
class WeatherViewModel : ViewModel() {
    val weatherData = WeatherDataRequest(WeatherInterface.create())

    private val _forecastResults = MutableLiveData<overview?>(null)
    val forecastResults: LiveData<overview?> = _forecastResults

    private val _loadingStatus = MutableLiveData(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    fun loadForecastResults(query: String?,temp_units: String?) {
        viewModelScope.launch {
            Log.d("blue","query results")
            _loadingStatus.value = LoadingStatus.LOADING
            val result = weatherData.loadForecast(query,temp_units)
            _forecastResults.value = result.getOrNull()
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }
}