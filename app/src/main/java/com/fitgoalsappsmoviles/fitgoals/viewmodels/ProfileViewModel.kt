package com.fitgoalsappsmoviles.fitgoals.viewmodels

import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.models.UserProfile
import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository

class ProfileViewModel : ViewModel() {
    private val userRepository = UserRepository()


    fun updateUserProfile(userProfile: UserProfile, callback: (Boolean) -> Unit) {
        val userId = userRepository.getCurrentUserId()
        if(userId != null) {
            userRepository.addUserProfile(userId, userProfile, callback)
        } else {
            callback(false)
        }
    }
}