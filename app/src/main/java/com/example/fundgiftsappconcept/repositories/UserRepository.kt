package com.example.fundgiftsappconcept.repositories

import androidx.lifecycle.MutableLiveData
import com.example.fundgiftsappconcept.ApiBuilder
import com.example.fundgiftsappconcept.LoginService
import com.example.fundgiftsappconcept.model.User
import java.lang.Error

class UserRepository {
    private val loginService: LoginService = ApiBuilder.createApi()
    val loginData: MutableLiveData<User> = MutableLiveData()



    suspend fun getUser() {
        try {
            val result = loginService.login()
            //loginData.value = result

        } catch (error: Throwable) {
            throw Error(
                    "Something went wrong while fetching Twitch user", error
            )
        }
    }
}