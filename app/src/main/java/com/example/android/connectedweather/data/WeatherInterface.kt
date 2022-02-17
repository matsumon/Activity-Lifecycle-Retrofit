package com.example.android.connectedweather.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherInterface {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ):overview

    //    copied and modified from Rob Hess's code https://github.com/osu-cs492-w22/LifecycleAwareGitHubSearch/blob/main/app/src/main/java/com/example/android/lifecyclegithubsearch/data/GitHubService.kt
    companion object{
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        fun create(): WeatherInterface{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .client(client)
                .build()
            return retrofit.create(WeatherInterface::class.java)
        }
    }
}