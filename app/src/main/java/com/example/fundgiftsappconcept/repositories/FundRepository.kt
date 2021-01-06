package com.example.fundgiftsappconcept.repositories

import androidx.lifecycle.MutableLiveData
import com.example.fundgiftsappconcept.api.ApiBuilder
import com.example.fundgiftsappconcept.api.FundService
import com.example.fundgiftsappconcept.model.Fund

class FundRepository {
    private val api: FundService = ApiBuilder.createApi()
    val funds: MutableLiveData<List<Fund>> = MutableLiveData()
    val userFunds: MutableLiveData<List<Fund>> = MutableLiveData()

    suspend fun getAllFunds() {
        try {
            funds.value = api.getAllFunds()
        } catch (error: Throwable) {
            throw RefreshError("Unable to refresh funds", error)
        }

    }

    suspend fun getUserFunds(Userid: String) {
        try {
            userFunds.value = api.getFundsPerUser(Userid)
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

