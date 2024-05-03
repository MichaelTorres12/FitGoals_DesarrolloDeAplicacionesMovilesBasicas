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

class ExerciseAdapter(private var exercises: List<Exercise>, private val context: Context) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.exerciseImageView)

        fun bind(exercise: Exercise, context: Context) {
            nameTextView.text = exercise.name
            Picasso.get().load(exercise.imageUrl).into(imageView)
            itemView.setOnClickListener {
                val intent = Intent(context, ExerciseDetailActivity::class.java).apply {
                    putExtra("EXERCISE_NAME", exercise.name)
                    putExtra("EXERCISE_IMAGE_URL", exercise.imageUrl)
                    putExtra("EXERCISE_SERIES", exercise.series)
                    putExtra("EXERCISE_DESCRIPTION", exercise.description)
                    putExtra("EXERCISE_VIDEO_URL", exercise.videoUrl)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exercises[position], context)
    }

    override fun getItemCount() = exercises.size

    fun updateExercises(newExercises: List<Exercise>) {
        exercises = newExercises
        notifyDataSetChanged()
    }
}
