package com.fitgoalsappsmoviles.fitgoals.repositories

// Repositorio que maneja la lógica de interacción con Firebase Auth y Firestore para los perfiles de usuario.
import android.util.Log
import com.fitgoalsappsmoviles.fitgoals.models.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Método para registrar un nuevo usuario con email y contraseña.
    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Firestore", "Usuario registrado con éxito")
                callback(true)
            } else {
                Log.d("Firestore", "Falló el registro del usuario")
                callback(false)
            }
        }
    }

    // Método para iniciar sesión de un usuario existente.
    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Firestore", "Inicio de sesión exitoso")
                callback(true)
            } else {
                Log.d("Firestore", "Falló el inicio de sesión")
                callback(false)
            }
        }
    }

    // Método para obtener el ID de usuario actual de FirebaseAuth.
    fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    // Método para agregar la información del perfil de un usuario a Firestore.
    fun addUserProfile(userProfile: UserProfile, callback: (Boolean) -> Unit) {
        val user = firebaseAuth.currentUser
        if (user != null) {
            val email = user.email ?: "Email no proporcionado"
            val userId = user.uid

            // Asegura que UserProfile tenga las propiedades email y userId.
            val updatedUserProfile = userProfile.copy(email = email, userId = userId)
            firestore.collection("userProfiles").document(userId).set(updatedUserProfile).addOnSuccessListener {
                callback(true)
            }.addOnFailureListener {
                callback(false)
            }
        } else {
            callback(false)
        }
    }

    // Método para obtener los datos del perfil de un usuario de Firestore.
    fun getUserProfile(userId: String, callback: (UserProfile?) -> Unit) {
        firestore.collection("userProfiles").document(userId).get().addOnSuccessListener { documentSnapshot ->
            val userProfile = documentSnapshot.toObject(UserProfile::class.java)
            callback(userProfile)
        }.addOnFailureListener {
            Log.e("Firestore", "Error al recuperar el perfil del usuario")
            callback(null)
        }
    }

    // Método para actualizar el peso del usuario en Firestore.
    fun updateWeight(userId: String, newWeight: Double, callback: (Boolean) -> Unit) {
        val weightUpdate = mapOf("weight" to newWeight)
        firestore.collection("userProfiles").document(userId).update(weightUpdate).addOnSuccessListener {
            Log.d("Firestore", "Peso actualizado con éxito")
            callback(true)
        }.addOnFailureListener {
            Log.e("Firestore", "Error al actualizar el peso")
            callback(false)
        }
    }

    // Método para cerrar sesión y limpiar la información del usuario.
    fun logOut() {
        firebaseAuth.signOut()
    }
}
