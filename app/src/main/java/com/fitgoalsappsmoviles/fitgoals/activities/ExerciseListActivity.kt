package com.fitgoalsappsmoviles.fitgoals.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.adapters.ExerciseAdapter
import com.fitgoalsappsmoviles.fitgoals.viewmodels.ExerciseViewModel

class ExerciseListActivity : AppCompatActivity() {
    private val viewModel: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)

        val muscleGroupId = intent.getStringExtra("muscleGroupId") ?: return
        // Pasamos 'this' como contexto al adapter
        val exerciseAdapter = ExerciseAdapter(emptyList(), this)

        val recyclerView = findViewById<RecyclerView>(R.id.rvExercises)
        recyclerView.adapter = exerciseAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.exercises.observe(this) { exercises ->
            exerciseAdapter.updateExercises(exercises)
        }

        viewModel.loadExercisesByMuscleGroup(muscleGroupId)
    }
}
