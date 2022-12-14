package com.example.android.connectedweather.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
//code copied and modified from Rob Hess: https://github.com/osu-cs492-w22/LifecycleAwareGitHubSearch/blob/main/app/src/main/java/com/example/android/lifecyclegithubsearch/data/GitHubReposRepository.kt
class WeatherDataRequest(
    val service: WeatherInterface,
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
)
{
    suspend fun loadForecast(query: String?,temp_units: String?): Result<overview> =
        withContext(ioDispatcher) {
//            Log.d("Blue","HERE IN DATA")
            try {
                var new_query = query ?: "Corvallis"
                var new_temp_units = temp_units ?: "metric"
                val results = service.getForecast(new_query,new_temp_units,"97a47cb2c6f46038cde0645cbf6d4224")
//                val results = service.getForecast(new_query,new_temp_units,"97a47cb2c6f46038cde0645cbf6d4224")
//                Log.d("Blue","In Data Request, $results")
                Result.success(results)
            } catch (e: Exception) {
//                Log.d("Blue","In Failure Data Request $e")
                Result.failure(e)
            }
        }
}