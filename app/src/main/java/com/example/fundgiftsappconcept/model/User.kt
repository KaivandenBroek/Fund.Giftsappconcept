package com.example.fundgiftsappconcept.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("_id") val id : String,
    @SerializedName("username") val name : String,
    @SerializedName("age") val age : String,
    @SerializedName("email") val email : String,
)