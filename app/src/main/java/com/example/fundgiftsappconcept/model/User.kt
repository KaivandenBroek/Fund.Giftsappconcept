package com.example.fundgiftsappconcept.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("user_id") val id : String,
    @SerializedName("friends") val friends : List<User>,
    @SerializedName("funds_id") val scopes : List<String>,
    @SerializedName("user_id") val userId : String,
    @SerializedName("expires_in") val expires : Int,
)