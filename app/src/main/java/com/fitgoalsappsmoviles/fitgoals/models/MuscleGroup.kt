package com.fitgoalsappsmoviles.fitgoals.models

// Modelo que representa un grupo muscular dentro de la colección "muscle_groups" en la base de datos.

data class MuscleGroup(
    val muscleGroupId: String = "",  // ID único del grupo muscular.
    val name: String = "",  // Nombre del grupo muscular.
    val imageUrl: String = ""  // URL de la imagen del grupo muscular.
)
