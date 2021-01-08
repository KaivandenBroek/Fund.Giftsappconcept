package com.example.fundgiftsappconcept.repositories

import androidx.lifecycle.MutableLiveData
import com.example.fundgiftsappconcept.api.ApiBuilder
import com.example.fundgiftsappconcept.api.FundService
import com.example.fundgiftsappconcept.model.User
import retrofit2.Response

class UserRepository {
    private val api: FundService = ApiBuilder.createApi()
    val users: MutableLiveData<List<User>> = MutableLiveData()

    suspend fun getUser(name: String): Response<User> {
        return api.getUser(name)
    }

    suspend fun getAllFriends() {
        try {
            users.value = api.getAllUsers()
        } catch (error: Throwable) {
            throw FundRepository.RefreshError("Unable to get users", error)
        }
    }

    suspend fun createUser(user: User) {
        try {
            api.createUser(user)
        } catch (error: Throwable) {
            throw FundRepository.RefreshError("Unable to create user", error)
        }
    }

    suspend fun deleteUser(id: String) {
        try {
            api.deleteUser(id)
        } catch (error: Throwable) {
            throw FundRepository.RefreshError("Unable to create user", error)
        }
    }

}