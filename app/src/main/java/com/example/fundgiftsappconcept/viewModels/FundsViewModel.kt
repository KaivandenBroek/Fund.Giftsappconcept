package com.example.fundgiftsappconcept.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundgiftsappconcept.repositories.FundRepository
import kotlinx.coroutines.launch

class FundViewmodel(application: Application) : AndroidViewModel(application) {

    private val fundRepo = FundRepository()
    val funds = fundRepo.funds

    fun inserFund() {
        viewModelScope.launch {
            try {
                fundRepo.insertFund()
            } catch (error: FundRepository.RefreshError) {
                Log.e("Trivia error", error.cause.toString())
            }
        }
    }
    }