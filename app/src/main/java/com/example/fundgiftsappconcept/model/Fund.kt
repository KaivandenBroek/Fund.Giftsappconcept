package com.example.fundgiftsappconcept.model

import com.google.gson.annotations.SerializedName

data class Fund(
        @SerializedName("_id") val id: String?,
        @SerializedName("fundname") val fundName: String,
        @SerializedName("current_amount") val currentAmount: Double,
        @SerializedName("full_amount") val fullAmount: Double,
        @SerializedName("user_id") val creatorId: String,
        )