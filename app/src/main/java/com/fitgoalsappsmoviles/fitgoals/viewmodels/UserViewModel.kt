package com.fitgoalsappsmoviles.fitgoals.viewmodels

import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository
import com.fitgoalsappsmoviles.fitgoals.services.AuthenticationService

class UserViewModel : ViewModel() {

    private val authService = AuthenticationService(UserRepository())

    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        authService.registerUser(email, password, callback)
    }
}
