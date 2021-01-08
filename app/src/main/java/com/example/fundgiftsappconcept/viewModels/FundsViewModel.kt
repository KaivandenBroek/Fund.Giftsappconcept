package com.example.fundgiftsappconcept.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundgiftsappconcept.CurrentUser
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.model.User
import com.example.fundgiftsappconcept.repositories.FundRepository
import com.example.fundgiftsappconcept.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FundViewmodel(application: Application) : AndroidViewModel(application) {

    private val fundRepo = FundRepository()
    private val userRepo = UserRepository()

    val funds = fundRepo.funds
    val myFunds = fundRepo.userFunds
    val friends = userRepo.users

    var user = CurrentUser.currentUser

    suspend fun getUserByUsername(name: String): Response<User> {
        return  userRepo.getUser(name)
    }

    fun setCurrentUser(user: User) {
        Log.e("Current user: ",user.name)
        CurrentUser.currentUser = user
    }


    fun insertUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepo.createUser(user)
        }
    }

    fun deleteUser(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepo.deleteUser(id)
        }
    }

    fun insertFund(fund: Fund) {
        CoroutineScope(Dispatchers.IO).launch {
            fundRepo.insertFund(fund)
        }
    }

    fun deleteFund(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            fundRepo.deleteFund(id)
        }
    }

    fun getAllFunds() {
        viewModelScope.launch {
            try {
                fundRepo.getAllFunds()
            } catch (error: FundRepository.RefreshError) {
                Log.e("all funds error", error.cause.toString())
            }
        }
    }

    fun getUserFunds() {
        val userId = user.id
        viewModelScope.launch {
            try {
                if (userId != null) {
                    fundRepo.getUserFunds(userId)
                }
            } catch (error: FundRepository.RefreshError) {
                Log.e("user funds error", error.cause.toString())
            }
        }
    }

    fun getAllFriends() {
        viewModelScope.launch {
            try {
                userRepo.getAllFriends()
            } catch (error: FundRepository.RefreshError) {
                Log.e("user list error", error.cause.toString())
            }
        }
    }
}