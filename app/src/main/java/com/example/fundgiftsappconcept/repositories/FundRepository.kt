package com.example.fundgiftsappconcept.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fundgiftsappconcept.ApiBuilder
import com.example.fundgiftsappconcept.FundService
import com.example.fundgiftsappconcept.model.Fund

class FundRepository {
    private val api: FundService = ApiBuilder.createApi()
    private val _funds: MutableLiveData<List<Fund>> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val funds: LiveData<List<Fund>>
        get() = _funds

    suspend fun getAllFunds() {
        try {
            _funds.value = api.getAllFunds()
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

    suspend fun insertFund() {
        try {
            api.insertFund()
        } catch (error: Throwable) {
            throw RefreshError("Unable to insert fund", error)
        }

    }
    suspend fun increaseFund(id: String, newAmount: Double) {
        try {
            api.increaseFund(id, newAmount)
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

