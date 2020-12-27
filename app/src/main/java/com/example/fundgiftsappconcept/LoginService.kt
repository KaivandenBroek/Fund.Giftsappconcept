package com.example.fundgiftsappconcept

import com.example.fundgiftsappconcept.model.User
import retrofit2.http.GET

interface LoginService {
    @GET("auth")
    suspend fun login() : User
}