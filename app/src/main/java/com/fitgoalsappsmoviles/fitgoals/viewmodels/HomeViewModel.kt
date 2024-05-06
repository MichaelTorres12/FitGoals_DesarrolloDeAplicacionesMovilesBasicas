package com.fitgoalsappsmoviles.fitgoals.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.models.MuscleGroup
import com.fitgoalsappsmoviles.fitgoals.repositories.MuscleGroupRepository

// ViewModel para el HomeFragment que gestiona la carga de grupos musculares.
class HomeViewModel : ViewModel() {
    private val muscleGroupRepository = MuscleGroupRepository()  // Instancia del repositorio.
    private val _muscleGroups = MutableLiveData<List<MuscleGroup>>()  // LiveData que contiene los grupos musculares.
    val muscleGroups: LiveData<List<MuscleGroup>> = _muscleGroups

    // Carga los grupos musculares desde el repositorio y actualiza el LiveData.
    fun loadMuscleGroups() {
        muscleGroupRepository.getMuscleGroups { groups ->
            _muscleGroups.value = groups
        }
    }
}

