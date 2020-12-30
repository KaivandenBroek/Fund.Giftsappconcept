package com.example.fundgiftsappconcept

import com.example.fundgiftsappconcept.model.User
import retrofit2.http.GET
import retrofit2.http.Headers

interface LoginService {
    @GET("/auth/twitch")
    suspend fun login()
}