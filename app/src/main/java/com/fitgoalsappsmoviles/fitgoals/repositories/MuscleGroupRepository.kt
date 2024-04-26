package com.fitgoalsappsmoviles.fitgoals.repositories

import android.util.Log
import com.fitgoalsappsmoviles.fitgoals.models.MuscleGroup
import com.google.firebase.firestore.FirebaseFirestore

class MuscleGroupRepository {
    private val db = FirebaseFirestore.getInstance()

    fun getMuscleGroups(callback: (List<MuscleGroup>) -> Unit) {
        db.collection("muscle_groups").get()
            .addOnSuccessListener { result ->
                val muscleGroups = result.toObjects(MuscleGroup::class.java)
                callback(muscleGroups)
            }
            .addOnFailureListener { exception ->
                Log.w("MuscleGroupRepository", "Error getting documents.", exception)
                callback(emptyList())
            }
    }
}
