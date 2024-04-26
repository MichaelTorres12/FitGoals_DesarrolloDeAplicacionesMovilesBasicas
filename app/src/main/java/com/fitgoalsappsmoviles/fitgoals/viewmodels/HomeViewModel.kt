package com.fitgoalsappsmoviles.fitgoals.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.models.MuscleGroup
import com.fitgoalsappsmoviles.fitgoals.repositories.MuscleGroupRepository

class HomeViewModel : ViewModel() {
    private val muscleGroupRepository = MuscleGroupRepository()
    private val _muscleGroups = MutableLiveData<List<MuscleGroup>>()
    val muscleGroups: LiveData<List<MuscleGroup>> = _muscleGroups

    fun loadMuscleGroups() {
        muscleGroupRepository.getMuscleGroups { groups ->
            _muscleGroups.value = groups
        }
    }
}
