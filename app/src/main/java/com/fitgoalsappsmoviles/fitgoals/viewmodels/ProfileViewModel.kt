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

    fun updateUserProfile(userProfileData: UserProfile, callback: (Boolean) -> Unit) {
        FirebaseAuth.getInstance().currentUser?.let { firebaseUser ->
            val email = firebaseUser.email
            val userId = firebaseUser.uid
            // Aseguramos que nuestra clase UserProfile incluye estas propiedades.
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

    fun addNewWeight(newWeight: Double) {
        val userId = userRepository.getCurrentUserId()
        if (userId != null) {
            userRepository.updateWeight(userId, newWeight) { isSuccessful ->
                if (isSuccessful) {
                    loadUserProfile()
                } else {
                    errorMessages.postValue("Error al actualizar el peso.")
                }
            }
        } else {
            errorMessages.postValue("Usuario no identificado.")
        }
    }

    fun logOut() {
        userRepository.logOut()
        userProfile.value = null
    }
}
