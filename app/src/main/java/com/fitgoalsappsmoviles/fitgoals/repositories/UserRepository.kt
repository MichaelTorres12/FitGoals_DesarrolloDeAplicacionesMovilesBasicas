package com.fitgoalsappsmoviles.fitgoals.repositories

import android.util.Log
import com.fitgoalsappsmoviles.fitgoals.models.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    //Registrar usuario nuevo
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

    //Logear usuario ya existente
    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Inicio de sesión exitoso
                callback(true)
            } else {
                // Inicio de sesión fallido, manejar excepción
                callback(false)
            }
        }
    }

    //Obtener el ID dentro de Auth de Firebase del usuario que se registró/logeó
    fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    //Agregar la informacion del perfil al respectivo usuario luego de haberse registrado
    fun addUserProfile(userId: String, userProfile: UserProfile, callback: (Boolean) -> Unit) {
        firestore.collection("userProfiles").document(userId).set(userProfile)
            .addOnSuccessListener {
                Log.d("Firestore", "Perfil del usuario guardado con éxito")
                callback(true)
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al guardar el perfil del usuario", exception)
                callback(false)
            }
    }

}