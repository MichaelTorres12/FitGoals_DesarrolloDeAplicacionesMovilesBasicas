package com.fitgoalsappsmoviles.fitgoals.services

// Servicio que actúa como intermediario entre las Activities y el UserRepository, gestionando las operaciones de autenticación.

import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository

class AuthenticationService(private val repository: UserRepository) {

    // Método para registrar nuevos usuarios pasando los datos requeridos al repositorio.
    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        repository.registerUser(email, password, callback)
    }

    // Método para iniciar sesión de un usuario validando los datos requeridos con el repositorio.
    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        repository.loginUser(email, password, callback)
    }
}
