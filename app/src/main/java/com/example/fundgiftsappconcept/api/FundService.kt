package com.example.fundgiftsappconcept.api

import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface FundService {

    //login stuff ///////////////////
    @GET("/auth/twitch")
    suspend fun login()

    //user stuff ///////////////////
    @POST("newUser")
    suspend fun createUser(@Body user: User)

    @GET("allUsers")
    suspend fun getAllUsers() : List<User>

    @GET("userByUsername/{username}")
    suspend fun getUser(@Path("username") username: String): Response<User>

    @DELETE("deleteUser/{id}")
    suspend fun deleteUser(@Path(value = "id") id: String)

    //fund stuff ///////////////////
    @GET("allFunds")
    suspend fun getAllFunds() : List<Fund>

    @GET("fundsByUserId/{id}")
    suspend fun getFundsPerUser(@Path("id") id: String) : List<Fund>

    @GET("funds/{id}")
    suspend fun getFund(@Path("id") id: String): Fund

    @Headers( "Content-Type: application/json" )
    @POST("newFund")
    suspend fun insertFund(@Body fund: Fund)

    @DELETE("deleteFund/{id}")
    suspend fun deleteFund(@Path(value = "id") id: String)
}