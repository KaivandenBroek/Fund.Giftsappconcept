package com.example.fundgiftsappconcept.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("client_id") val clientId : String,
    @SerializedName("login") val login : String,
    @SerializedName("scopes") val scopes : List<String>,
    @SerializedName("user_id") val userId : String,
    @SerializedName("expires_in") val expires : Int,
)