package com.fitgoalsappsmoviles.fitgoals.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.adapters.MuscleGroupAdapter
import com.fitgoalsappsmoviles.fitgoals.activities.ExerciseListActivity
import com.fitgoalsappsmoviles.fitgoals.viewmodels.HomeViewModel

// HomeFragment sirve como una vista para mostrar las categorías de grupos musculares almacenados en la base de datos.
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()  // Inicialización del ViewModel para obtener datos.
    private lateinit var adapter: MuscleGroupAdapter  // Adaptador para manejar la visualización de datos en un RecyclerView.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Configuración del RecyclerView que muestra los grupos musculares.
        val muscleGroupRecyclerView: RecyclerView = view.findViewById(R.id.rvMuscleGroups)
        adapter = MuscleGroupAdapter(emptyList()) { muscleGroupId ->
            val intent = Intent(context, ExerciseListActivity::class.java)
            intent.putExtra("muscleGroupId", muscleGroupId)  // Intención para mostrar detalles del grupo muscular.
            startActivity(intent)
        }
        muscleGroupRecyclerView.adapter = adapter
        muscleGroupRecyclerView.layoutManager = LinearLayoutManager(context)

        // Observa cambios en los datos de los grupos musculares y actualiza el RecyclerView.
        viewModel.muscleGroups.observe(viewLifecycleOwner) { groups ->
            adapter.updateMuscleGroups(groups)  // Actualización de los datos del adaptador.
        }

        viewModel.loadMuscleGroups()  // Solicita la carga de los grupos musculares desde el repositorio.

        return view
    }
}


