package com.example.fundgiftsappconcept

import com.example.fundgiftsappconcept.model.User

class CurrentUser {
    companion object {
        var currentUser: User = User(
                id = null,
                name = "",
                age = "",
                email = ""
        )
    }
}