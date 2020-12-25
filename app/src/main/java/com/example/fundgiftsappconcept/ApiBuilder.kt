package com.example.fundgiftsappconcept

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
    private const val URL = "https://localhostNOGWATTES"

    fun createApi(): LoginService{
        // Create an OkHttpClient to be able to make a log of the network traffic
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        // Create the Retrofit instance
        val twitchApi = Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Return the Retrofit TriviaApiService
        return twitchApi.create(LoginService::class.java)
    }
}