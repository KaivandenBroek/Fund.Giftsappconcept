package com.example.fundgiftsappconcept.model

import com.google.gson.annotations.SerializedName

data class Fund (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("user_id") val creator : User,
    @SerializedName("full_amount") val fullAmount : Double,
    @SerializedName("current_amount") val currentAmount : Double,

    )