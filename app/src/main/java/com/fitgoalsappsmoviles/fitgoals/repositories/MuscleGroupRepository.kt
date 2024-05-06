package com.fitgoalsappsmoviles.fitgoals.repositories

import android.util.Log
import com.fitgoalsappsmoviles.fitgoals.models.MuscleGroup
import com.google.firebase.firestore.FirebaseFirestore

// Repositorio para manejar la interacción con la colección de grupos musculares en Firestore.
class MuscleGroupRepository {
    private val db = FirebaseFirestore.getInstance()  // Instancia de Firestore.

    // Obtiene los grupos musculares de Firestore y ejecuta un callback con los resultados.
    fun getMuscleGroups(callback: (List<MuscleGroup>) -> Unit) {
        db.collection("muscle_groups").get()
            .addOnSuccessListener { result ->
                val muscleGroups = result.toObjects(MuscleGroup::class.java)  // Convierte los documentos a objetos MuscleGroup.
                callback(muscleGroups)
            }
            .addOnFailureListener { exception ->
                Log.w("MuscleGroupRepository", "Error getting documents.", exception)
                callback(emptyList())  // Devuelve una lista vacía en caso de error.
            }
    }
}

