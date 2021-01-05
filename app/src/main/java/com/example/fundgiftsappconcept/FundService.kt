package com.example.fundgiftsappconcept

import androidx.room.Delete
import androidx.room.Update
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.model.User
import retrofit2.Response
import retrofit2.http.*

interface FundService {

    //login stuff ///////////////////
    @GET("/auth/twitch")
    suspend fun login()

    //user stuff ///////////////////
    @POST()
    suspend fun createUser()

    @POST("userByUsername")
    suspend fun getUser(@Body username: String): User

    //fund stuff ///////////////////
    @GET("allFunds")
    suspend fun getAllFunds() : List<Fund>

    @GET("funds/{id}")
    suspend fun getFund(@Path("id") id: String): Fund

    @Headers( "Content-Type: application/json" )
    @POST("newFund")
    suspend fun insertFund(@Body fund: Fund)

    @DELETE("deleteFund/{id}")
    suspend fun deleteFund(@Path(value = "id") id: String)

    @Update()
    suspend fun increaseFund(@Path("Id") id: String, newAmount: Double) : Fund
}