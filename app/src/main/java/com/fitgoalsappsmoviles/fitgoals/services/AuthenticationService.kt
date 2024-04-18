package com.fitgoalsappsmoviles.fitgoals.services
/*
* AuthenticationService actúa como una capa de abstracción sobre el UserRepository.
* */

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository

class AuthenticationService(private val repository: UserRepository) {

    //Registrar usuario nuevos con los datos requeridos
    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        repository.registerUser(email, password, callback)
    }

    //Logear al usuario validando los datos requeridos
    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        repository.loginUser(email, password, callback)
    }
}