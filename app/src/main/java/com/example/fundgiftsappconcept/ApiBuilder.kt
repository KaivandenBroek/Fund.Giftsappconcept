package com.example.fundgiftsappconcept

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
    private const val URL = "http://10.0.2.2:5000"

    fun createApi(): FundService{
        // Create an OkHttpClient to be able to make a log of the network traffic
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        // Gson converter with lenient to retrieve twitch data
        val gson =  GsonBuilder()
                .setLenient()
                .create()

        // Create the Retrofit instance
        val twitchApi = Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        // Return the Retrofit TriviaApiService
        return twitchApi.create(FundService::class.java)
    }
}