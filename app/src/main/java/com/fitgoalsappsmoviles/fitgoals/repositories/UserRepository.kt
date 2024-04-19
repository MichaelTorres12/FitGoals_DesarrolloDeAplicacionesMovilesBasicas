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
    fun addUserProfile(userProfile: UserProfile, callback: (Boolean) -> Unit) {
        val user = firebaseAuth.currentUser // Obtiene el usuario actual
        if (user != null) {
            val email = user.email ?: "email no proporcionado"
            val userId = user.uid

            // Asegúrate de que el modelo UserProfile tenga las propiedades email y userId.
            val updatedUserProfile = userProfile.copy(email = email, userId = userId)

            firestore.collection("userProfiles").document(userId).set(updatedUserProfile)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener { exception ->
                    callback(false)
                }
        } else {
            callback(false)
        }
    }


    //Conseguir o jalar los datos del perfil desde la coleccion de Firestore useProfiles que corresponden al Id en cuestion.
    fun getUserProfile(userId: String, callback: (UserProfile?) -> Unit) {
        firestore.collection("userProfiles").document(userId).get()
            .addOnSuccessListener { documentSnapshot ->
                val userProfile = documentSnapshot.toObject(UserProfile::class.java)
                callback(userProfile)
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al recuperar el perfil del usuario", exception)
                callback(null)
            }
    }

    //Actualizar en la coleccion de firestore el peso "weight" del usuario para poder llevar un historial de pesos
    fun updateWeight(userId: String, newWeight: Double, callback: (Boolean) -> Unit) {
        val weightUpdate = mapOf("weight" to newWeight)
        firestore.collection("userProfiles").document(userId).update(weightUpdate)
            .addOnSuccessListener {
                Log.d("Firestore", "Peso actualizado con éxito")
                callback(true)
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error al actualizar el peso", exception)
                callback(false)
            }
    }

    //Cerrar la sesion del perfil actual y regresar a la vista del welcome
    fun logOut() {
        firebaseAuth.signOut()
    }

}