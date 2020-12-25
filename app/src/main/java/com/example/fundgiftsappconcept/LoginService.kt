package com.example.fundgiftsappconcept

import retrofit2.http.GET

interface LoginService {
    @GET("auth")
    suspend fun login()
}