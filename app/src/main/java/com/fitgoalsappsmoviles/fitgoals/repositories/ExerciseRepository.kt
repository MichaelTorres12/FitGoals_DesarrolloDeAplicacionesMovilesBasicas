package com.fitgoalsappsmoviles.fitgoals.repositories

//Repositorio del modelo Exercise que se encarga de interactuar con la Base de Datos.

import com.google.firebase.firestore.FirebaseFirestore
import com.fitgoalsappsmoviles.fitgoals.models.Exercise

// Repositorio para gestionar la recuperación de ejercicios de Firestore basados en el grupo muscular.
class ExerciseRepository {
    private val db = FirebaseFirestore.getInstance()  // Acceso a Firestore.

    // Consulta a Firestore para obtener ejercicios filtrados por grupo muscular filtrando a traves del ID de muscleGroupId.
    fun getExercisesByMuscleGroup(muscleGroupId: String, callback: (List<Exercise>) -> Unit) {
        db.collection("Exercises").whereEqualTo("muscleGroupId", muscleGroupId).get()
            .addOnSuccessListener { result ->
                val exercises = result.toObjects(Exercise::class.java)
                callback(exercises)  // Devuelve la lista de ejercicios.
            }
            .addOnFailureListener { exception ->
                println("Error getting exercises: $exception")
                callback(emptyList())  // Manejo de errores.
            }
    }
}
