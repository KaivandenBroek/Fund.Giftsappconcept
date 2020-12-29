package com.example.fundgiftsappconcept.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundgiftsappconcept.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewmodel(application: Application) : AndroidViewModel(application) {

    private val userRepo = UserRepository()
    //val user = userRepo.getUser()

    fun setUser() {
        viewModelScope.launch {
            userRepo.getUser()
        }
    }
}