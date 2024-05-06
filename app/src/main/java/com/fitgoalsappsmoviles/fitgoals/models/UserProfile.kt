package com.fitgoalsappsmoviles.fitgoals.models

//Modelo de los campos pertenecientes a un documento dentro de la colección "userProfiles"

// Modelo que representa un perfil de usuario en la base de datos de Firestore.
data class UserProfile(
    val weight: Double? = null,    // Peso del usuario.
    val height: Double? = null,    // Altura del usuario.
    val gender: String? = null,    // Género del usuario.
    val age: Int? = null,          // Edad del usuario.
    val userId: String? = null,   // ID único del usuario (Firebase Auth ID).
    val email: String? = null     // Correo electrónico del usuario.
)