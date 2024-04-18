package com.fitgoalsappsmoviles.fitgoals.repositories

import com.google.firebase.auth.FirebaseAuth

class UserRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    callback(true)
                } else {
                    // Registro fallido, manejar excepción
                    callback(false)
                }
            }
    }
}