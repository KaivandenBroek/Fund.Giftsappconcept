package com.example.fundgiftsappconcept

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.fundgiftsappconcept.model.Fund
import retrofit2.http.GET
import retrofit2.http.Path

interface FundService {

    //login stuff ///////////////////
    @GET("/auth/twitch")
    suspend fun login()

    //fund stuff ///////////////////
    @GET("/funds/all")
    suspend fun getAllFunds() : List<Fund>

    @GET("/funds/{Id}")
    suspend fun getFund(@Path("Id") id: String) : Fund

    @Insert
    suspend fun insertFund() : Fund

    @Delete()
    suspend fun deleteFund(@Path("Id") id: String)

    @Update()
    suspend fun increaseFund(@Path("Id") id: String, newAmount: Double) : Fund
}