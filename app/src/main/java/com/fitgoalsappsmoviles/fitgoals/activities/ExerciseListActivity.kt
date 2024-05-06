package com.fitgoalsappsmoviles.fitgoals.activities

//Activity que enlista todos los ejercicios que pertenecen a un grupo muscular especifico.

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.adapters.ExerciseAdapter
import com.fitgoalsappsmoviles.fitgoals.viewmodels.ExerciseViewModel

// Activity que muestra una lista de ejercicios asociados a un grupo muscular específico.
class ExerciseListActivity : AppCompatActivity() {
    private val viewModel: ExerciseViewModel by viewModels()  // ViewModel que gestiona los datos de los ejercicios.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)

        // Obtiene el ID del grupo muscular desde el Fragmento Home en la Activity de MainActivity.
        val muscleGroupId = intent.getStringExtra("muscleGroupId") ?: return
        val exerciseAdapter = ExerciseAdapter(emptyList(), this)

        // Configuración del RecyclerView para mostrar los ejercicios desde la DB en Firestore.
        val recyclerView = findViewById<RecyclerView>(R.id.rvExercises)
        recyclerView.adapter = exerciseAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observa cambios en los datos de ejercicios y actualiza el RecyclerView.
        viewModel.exercises.observe(this) { exercises ->
            exerciseAdapter.updateExercises(exercises)
        }

        // Solicita cargar los ejercicios para el grupo muscular especificado.
        viewModel.loadExercisesByMuscleGroup(muscleGroupId)
    }
}

