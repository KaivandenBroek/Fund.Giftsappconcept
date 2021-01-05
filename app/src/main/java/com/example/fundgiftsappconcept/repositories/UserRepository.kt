package com.example.fundgiftsappconcept.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fundgiftsappconcept.ApiBuilder
import com.example.fundgiftsappconcept.FundService
import com.example.fundgiftsappconcept.model.User

class UserRepository {
    private val api: FundService = ApiBuilder.createApi()
    val users: MutableLiveData<List<User>> = MutableLiveData()

    suspend fun getUser(name: String): User {
        try {
            Log.v("GETTING: ", name)
            return api.getUser(name)
        } catch (error: Throwable) {
            throw FundRepository.RefreshError("Unable to get user", error)
        }
    }
}