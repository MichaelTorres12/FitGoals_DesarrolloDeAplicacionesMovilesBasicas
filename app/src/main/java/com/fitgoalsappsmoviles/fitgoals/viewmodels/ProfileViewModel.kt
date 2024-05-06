package com.fitgoalsappsmoviles.fitgoals.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.models.UserProfile
import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {

    private val userRepository = UserRepository()
    val userProfile = MutableLiveData<UserProfile>()
    val errorMessages = MutableLiveData<String>()

    // Método para actualizar el perfil de usuario en Firestore.
    fun updateUserProfile(userProfileData: UserProfile, callback: (Boolean) -> Unit) {
        FirebaseAuth.getInstance().currentUser?.let { firebaseUser ->
            val email = firebaseUser.email
            val userId = firebaseUser.uid

            // Asegura que nuestra clase UserProfile incluye email y userId.
            val updatedUserProfile = userProfileData.copy(email = email, userId = userId)
            userRepository.addUserProfile(updatedUserProfile) { isSuccessful ->
                if (isSuccessful) {
                    userProfile.postValue(updatedUserProfile)
                    callback(true)
                } else {
                    errorMessages.postValue("Error al actualizar el perfil del usuario.")
                    callback(false)
                }
            }
        } ?: run {
            errorMessages.postValue("No se ha iniciado sesión.")
            callback(false)
        }
    }

    // Método para cargar el perfil de usuario desde Firestore.
    fun loadUserProfile() {
        val userId = userRepository.getCurrentUserId()
        userId?.let {
            userRepository.getUserProfile(it) { profile ->
                profile?.let {
                    userProfile.postValue(it)
                } ?: run {
                    errorMessages.postValue("No se encontraron datos de perfil.")
                }
            }
        } ?: run {
            errorMessages.postValue("Usuario no identificado.")
        }
    }

    // Método para añadir un nuevo peso al perfil del usuario y actualizar en Firestore.
    fun addNewWeight(newWeight: Double) {
        val userId = userRepository.getCurrentUserId()
        if (userId != null) {
            userRepository.updateWeight(userId, newWeight) { isSuccessful ->
                if (isSuccessful) {
                    loadUserProfile() // Recarga los datos del perfil actualizados.
                } else {
                    errorMessages.postValue("Error al actualizar el peso.")
                }
            }
        } else {
            errorMessages.postValue("Usuario no identificado.")
        }
    }

    // Método para cerrar la sesión del usuario actual.
    fun logOut() {
        userRepository.logOut() // Llama al método logOut de UserRepository para cerrar la sesión.
        userProfile.value = null // Limpia los datos del perfil del usuario en la vista.
    }
}
