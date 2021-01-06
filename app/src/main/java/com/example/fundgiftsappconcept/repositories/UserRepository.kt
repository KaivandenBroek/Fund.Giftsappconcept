package com.example.fundgiftsappconcept.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fundgiftsappconcept.CurrentUser
import com.example.fundgiftsappconcept.api.ApiBuilder
import com.example.fundgiftsappconcept.api.FundService
import com.example.fundgiftsappconcept.model.User

class UserRepository {
    private val api: FundService = ApiBuilder.createApi()
    val users: MutableLiveData<List<User>> = MutableLiveData()

    suspend fun getUser(name: String): User {
        try {
            CurrentUser.currentUser = api.getUser(name)
            Log.v("Current user: ", CurrentUser.currentUser.name)
            return api.getUser(name)
        } catch (error: Throwable) {
            throw FundRepository.RefreshError("Unable to get user", error)
        }
    }

    suspend fun getAllFriends() {
        try {
            users.value = api.getAllUsers()
        } catch (error: Throwable) {
            throw FundRepository.RefreshError("Unable to get user", error)
        }
    }

    suspend fun createUser(user: User) {
        try {
            api.createUser(user)
        } catch (error: Throwable) {
            throw FundRepository.RefreshError("Unable to create user", error)
        }
    }
}