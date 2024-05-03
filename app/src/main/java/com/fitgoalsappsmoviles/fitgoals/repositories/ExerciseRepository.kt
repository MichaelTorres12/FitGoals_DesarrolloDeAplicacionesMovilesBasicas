package com.fitgoalsappsmoviles.fitgoals.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.fitgoalsappsmoviles.fitgoals.models.Exercise

class ExerciseRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getExercisesByMuscleGroup(muscleGroupId: String, callback: (List<Exercise>) -> Unit) {
        db.collection("Exercises")
            .whereEqualTo("muscleGroupId", muscleGroupId)
            .get()
            .addOnSuccessListener { result ->
                val exercises = result.toObjects(Exercise::class.java)
                println("Fetched exercises with hardcoded ID: ${exercises.size}")
                callback(exercises)
            }
            .addOnFailureListener { exception ->
                println("Error getting exercises: $exception")
                callback(emptyList())
            }
    }
}
