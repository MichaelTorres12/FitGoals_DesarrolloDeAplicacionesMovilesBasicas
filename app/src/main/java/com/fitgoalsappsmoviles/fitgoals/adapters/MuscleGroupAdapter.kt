package com.fitgoalsappsmoviles.fitgoals.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.models.MuscleGroup
import com.squareup.picasso.Picasso

class MuscleGroupAdapter(private var muscleGroups: List<MuscleGroup>) :
    RecyclerView.Adapter<MuscleGroupAdapter.MuscleGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_muscle_group, parent, false)
        return MuscleGroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: MuscleGroupViewHolder, position: Int) {
        holder.bind(muscleGroups[position])
    }

    override fun getItemCount() = muscleGroups.size

    fun updateMuscleGroups(newMuscleGroups: List<MuscleGroup>) {
        this.muscleGroups = newMuscleGroups
        notifyDataSetChanged()
    }

    class MuscleGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.muscleGroupNameTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.muscleGroupImageView)

        fun bind(muscleGroup: MuscleGroup) {
            nameTextView.text = muscleGroup.name
            Picasso.get().load(muscleGroup.imageUrl).into(imageView)
        }
    }
}
