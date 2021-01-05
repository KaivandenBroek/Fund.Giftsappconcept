package com.example.fundgiftsappconcept.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundgiftsappconcept.model.Fund
import com.example.fundgiftsappconcept.model.User
import com.example.fundgiftsappconcept.repositories.FundRepository
import com.example.fundgiftsappconcept.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FundViewmodel(application: Application) : AndroidViewModel(application) {

    private val fundRepo = FundRepository()
    private val userRepo = UserRepository()
    val funds = fundRepo.funds
    var currentUser: User? = null

    fun getUserByUsername(name: String): User? {
        viewModelScope.launch {
            currentUser = userRepo.getUser(name)
        }
        return currentUser
    }

    fun insertFund(fund: Fund) {
        CoroutineScope(Dispatchers.Main).launch {
            Log.v("Creating: ", fund.fundName)
            fundRepo.insertFund(fund)
        }
    }

    fun deleteFund(id: String) {
        CoroutineScope(Dispatchers.Main).launch {
            fundRepo.deleteFund(id)
        }
    }

    fun getAllFunds() {
        viewModelScope.launch {
            try {
                fundRepo.getAllFunds()
            } catch (error: FundRepository.RefreshError) {
                Log.e("Trivia error", error.cause.toString())
            }
        }
    }
}