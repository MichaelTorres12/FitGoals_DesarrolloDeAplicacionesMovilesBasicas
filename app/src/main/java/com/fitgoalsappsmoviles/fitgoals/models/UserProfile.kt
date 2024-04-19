//Modelo de los datos del perfil del usuario, defino los datos/variables, el tipo y que no pueden ser iguales a null

package com.fitgoalsappsmoviles.fitgoals.models

data class UserProfile(
    val weight: Double? = null,
    val height: Double? = null,
    val gender: String? = null,
    val age: Int? = null,
    val userId: String? = null,
    val email: String? = null
)