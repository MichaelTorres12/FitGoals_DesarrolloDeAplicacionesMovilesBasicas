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
import com.fitgoalsappsmoviles.fitgoals.activities.CreditosActivity
import com.fitgoalsappsmoviles.fitgoals.activities.WelcomeActivityApp
import com.fitgoalsappsmoviles.fitgoals.viewmodels.ProfileViewModel

// ProfileFragment permite al usuario ver y actualizar su información de perfil y cerrar sesión, al igual que navegar a la vista de los creditos.
class ProfileFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModels()  // ViewModel que maneja la información del perfil del usuario.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Vincula los componentes de la UI con las variables.
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvAge = view.findViewById<TextView>(R.id.tvAge)
        val tvGender = view.findViewById<TextView>(R.id.tvGender)
        val tvHeight = view.findViewById<TextView>(R.id.tvHeight)
        val tvWeight = view.findViewById<TextView>(R.id.tvWeightControl)
        val etNewWeight = view.findViewById<EditText>(R.id.etNewWeight)
        val btnUpdateWeight = view.findViewById<Button>(R.id.btnUpdateWeight)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        // Observa los cambios en el perfil del usuario y actualiza la UI.
        profileViewModel.userProfile.observe(viewLifecycleOwner) { userProfile ->
            userProfile?.let {
                tvEmail.text = getString(R.string.email_label, it.email)
                tvAge.text = getString(R.string.age_label, it.age.toString())
                tvGender.text = getString(R.string.gender_label, it.gender)
                tvHeight.text = getString(R.string.height_label, it.height.toString())
                tvWeight.text = getString(R.string.weight_label, it.weight.toString())
            }
        }

        // Actualiza el peso del usuario en la base de datos al hacer clic en el botón.
        btnUpdateWeight.setOnClickListener {
            val newWeightText = etNewWeight.text.toString()
            if (newWeightText.isNotEmpty()) {
                val newWeight = newWeightText.toDouble()
                profileViewModel.addNewWeight(newWeight)  // Llama al ViewModel para actualizar el peso.
                etNewWeight.text.clear()  // Limpia el campo de texto.
            } else {
                Toast.makeText(context, "Por favor, introduce un peso válido.", Toast.LENGTH_LONG).show()
            }
        }

        // Cierra la sesión del usuario y regresa a la pantalla de bienvenida.
        btnLogout.setOnClickListener {
            profileViewModel.logOut()
            val intent = Intent(activity, WelcomeActivityApp::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }

        // Navega a la pantalla de créditos al hacer clic en el botón correspondiente.
        val btnSeeCredits: Button = view.findViewById(R.id.btnSeeCredits)
        btnSeeCredits.setOnClickListener {
            val intent = Intent(activity, CreditosActivity::class.java)
            startActivity(intent)
        }

        profileViewModel.loadUserProfile()  // Carga la información del perfil del usuario al iniciar la vista.

        return view
    }
}


