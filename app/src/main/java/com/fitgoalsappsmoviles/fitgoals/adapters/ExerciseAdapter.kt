package com.fitgoalsappsmoviles.fitgoals.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.activities.ExerciseDetailActivity
import com.fitgoalsappsmoviles.fitgoals.models.Exercise
import com.squareup.picasso.Picasso

// Adapter para gestionar la presentación de ejercicios en un RecyclerView.
class ExerciseAdapter(private var exercises: List<Exercise>, private val context: Context) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        // Infla el layout para cada elemento de la lista.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        // Asocia los datos del ejercicio con el holder.

        /*Observe que OnBindViewHolder es el código que se ocupa directamente de la estructura de
        los datos. En este caso, OnBindViewHolder comprende cómo asignar la posición del elemento
        RecyclerView a su elemento de datos asociado en el origen de datos.*/

        holder.bind(exercises[position], context)
    }

    override fun getItemCount() = exercises.size

    // Actualiza la lista de ejercicios y notifica cambios al RecyclerView.
    fun updateExercises(newExercises: List<Exercise>) {
        exercises = newExercises
        notifyDataSetChanged()
    }

    // ViewHolder que describe la vista de un ítem y metadata sobre su lugar en el RecyclerView.
    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.exerciseImageView)

        fun bind(exercise: Exercise, context: Context) {
            nameTextView.text = exercise.name
            Picasso.get().load(exercise.imageUrl).into(imageView)  // Carga la imagen desde URL en la DB.
            itemView.setOnClickListener {
                val intent = Intent(context, ExerciseDetailActivity::class.java).apply {

                    // Envía detalles del ejercicio a la siguiente actividad.
                    putExtra("EXERCISE_NAME", exercise.name)
                    putExtra("EXERCISE_IMAGE_URL", exercise.imageUrl)
                    putExtra("EXERCISE_SERIES", exercise.series)
                    putExtra("EXERCISE_DESCRIPTION", exercise.description)
                    putExtra("EXERCISE_VIDEO_URL", exercise.videoUrl)
                }

                //Se inicializa la Activity usando el Intent
                context.startActivity(intent)
            }
        }
    }
}

