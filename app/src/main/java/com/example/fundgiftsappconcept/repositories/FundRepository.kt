package com.example.fundgiftsappconcept.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fundgiftsappconcept.ApiBuilder
import com.example.fundgiftsappconcept.FundService
import com.example.fundgiftsappconcept.model.Fund
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FundRepository {
    private val api: FundService = ApiBuilder.createApi()
    val funds: MutableLiveData<List<Fund>> = MutableLiveData()

    suspend fun getAllFunds() {
        try {
            funds.value = api.getAllFunds()
            Log.v("RESPONSE1: ", funds.value.toString())
        } catch (error: Throwable) {
            throw RefreshError("Unable to refresh funds", error)
        }

    }

    suspend fun getFund(id: String) {
        try {
            api.getFund(id)
        } catch (error: Throwable) {
            throw RefreshError("Unable to get fund", error)
        }

    }

    // also increases fund if Fund object has same name
    suspend fun insertFund(fund: Fund) {
        try {
            Log.v("BODY", fund.toString())
            api.insertFund(fund)
        } catch (error: Throwable) {
            throw RefreshError("Unable to insert fund", error)
        }

    }

    suspend fun deleteFund(id: String) {
        try {
            api.deleteFund(id)
        } catch (error: Throwable) {
            throw RefreshError("Unable to delete fund", error)
        }

    }
    class RefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}
