package com.fitgoalsappsmoviles.fitgoals.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.activities.WelcomeActivityApp
import com.fitgoalsappsmoviles.fitgoals.viewmodels.ProfileViewModel

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvAge = view.findViewById<TextView>(R.id.tvAge)
        val tvGender = view.findViewById<TextView>(R.id.tvGender)
        val tvHeight = view.findViewById<TextView>(R.id.tvHeight)
        val tvWeight = view.findViewById<TextView>(R.id.tvWeightControl)
        val etNewWeight = view.findViewById<EditText>(R.id.etNewWeight)
        val btnUpdateWeight = view.findViewById<Button>(R.id.btnUpdateWeight)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // Observa los datos del perfil del usuario
        profileViewModel.userProfile.observe(viewLifecycleOwner) { userProfile ->
            userProfile?.let {
                // Actualizar la UI con los datos del perfil
                tvEmail.text = getString(R.string.email_label, it.email)
                tvAge.text = getString(R.string.age_label, it.age.toString())
                tvGender.text = getString(R.string.gender_label, it.gender)
                tvHeight.text = getString(R.string.height_label, it.height.toString())
                tvWeight.text = getString(R.string.weight_label, it.weight.toString())
                // tvPreviousWeights.text = ... Debes actualizar esto según tu modelo y base de datos
            }
        }

        // Observa mensajes de error
        profileViewModel.errorMessages.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                profileViewModel.errorMessages.value = "" // Resetear el mensaje de error después de mostrarlo
            }
        }

        btnUpdateWeight.setOnClickListener {
            val newWeightText = etNewWeight.text.toString()
            if (newWeightText.isNotEmpty()) {
                val newWeight = newWeightText.toDouble()
                profileViewModel.addNewWeight(newWeight)
                etNewWeight.text.clear()
            } else {
                Toast.makeText(context, "Por favor, introduce un peso válido.", Toast.LENGTH_LONG).show()
            }
        }

        btnLogout.setOnClickListener {
            profileViewModel.logOut()

            val intent = Intent(activity, WelcomeActivityApp::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            activity?.finish()
        }

        // Iniciar la carga de datos del perfil del usuario
        profileViewModel.loadUserProfile()

        return view
    }
}

