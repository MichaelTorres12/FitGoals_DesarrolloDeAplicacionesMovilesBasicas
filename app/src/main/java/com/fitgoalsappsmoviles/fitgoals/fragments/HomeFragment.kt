package com.fitgoalsappsmoviles.fitgoals.fragments

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
import com.fitgoalsappsmoviles.fitgoals.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: MuscleGroupAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val muscleGroupRecyclerView: RecyclerView = view.findViewById(R.id.rvMuscleGroups)
        adapter = MuscleGroupAdapter(emptyList())
        muscleGroupRecyclerView.adapter = adapter
        muscleGroupRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.muscleGroups.observe(viewLifecycleOwner) { groups ->
            adapter.updateMuscleGroups(groups)
        }

        viewModel.loadMuscleGroups()

        return view
    }
}
