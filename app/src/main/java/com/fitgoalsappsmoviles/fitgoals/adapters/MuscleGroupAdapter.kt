package com.fitgoalsappsmoviles.fitgoals.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.activities.ExerciseListActivity
import com.fitgoalsappsmoviles.fitgoals.models.MuscleGroup
import com.squareup.picasso.Picasso

// Adapter para manejar la presentación de grupos musculares en un RecyclerView.
class MuscleGroupAdapter(private var muscleGroups: List<MuscleGroup>, private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<MuscleGroupAdapter.MuscleGroupViewHolder>() {

    // Crea nuevas vistas (invocado por el layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuscleGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_muscle_group, parent, false)
        return MuscleGroupViewHolder(view, onClick)
    }

    // Reemplaza el contenido de una vista (invocado por el layout manager)
    override fun onBindViewHolder(holder: MuscleGroupViewHolder, position: Int) {
        holder.bind(muscleGroups[position])
    }

    // Retorna el tamaño del conjunto de datos (invocado por el layout manager)
    override fun getItemCount() = muscleGroups.size

    // Actualiza el conjunto de datos del adaptador y notifica cualquier cambio para re-renderizar la vista.
    fun updateMuscleGroups(newMuscleGroups: List<MuscleGroup>) {
        this.muscleGroups = newMuscleGroups
        notifyDataSetChanged()
    }

    // Proporciona una referencia a las vistas para cada elemento de datos
    class MuscleGroupViewHolder(itemView: View, private val onClick: (String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.muscleGroupNameTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.muscleGroupImageView)

        // Vincula los datos del objeto MuscleGroup con los elementos de la vista.
        fun bind(muscleGroup: MuscleGroup) {
            nameTextView.text = muscleGroup.name
            Picasso.get().load(muscleGroup.imageUrl).into(imageView)  // Carga imágenes con Picasso.
            itemView.setOnClickListener { onClick(muscleGroup.muscleGroupId) }
        }
    }
}
