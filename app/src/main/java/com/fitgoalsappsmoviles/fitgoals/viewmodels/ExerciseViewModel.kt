package com.fitgoalsappsmoviles.fitgoals.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.models.Exercise
import com.fitgoalsappsmoviles.fitgoals.repositories.ExerciseRepository

class ExerciseViewModel : ViewModel() {
    private val exerciseRepository = ExerciseRepository()
    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> = _exercises

    fun loadExercisesByMuscleGroup(muscleGroupId: String) {
        exerciseRepository.getExercisesByMuscleGroup(muscleGroupId) { exercises ->
            _exercises.postValue(exercises)
        }
    }
}
