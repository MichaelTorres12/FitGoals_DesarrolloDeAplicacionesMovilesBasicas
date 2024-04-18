package com.fitgoalsappsmoviles.fitgoals.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository

class AuthenticationService(private val repository: UserRepository) {

    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        repository.registerUser(email, password, callback)
    }
}