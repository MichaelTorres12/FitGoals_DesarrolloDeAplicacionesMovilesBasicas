package com.fitgoalsappsmoviles.fitgoals.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fitgoalsappsmoviles.fitgoals.MainActivity
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.models.UserProfile
import com.fitgoalsappsmoviles.fitgoals.viewmodels.ProfileViewModel

class ProfileCustomizationActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_customization)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val editTextWeight = findViewById<EditText>(R.id.editTextCurrentWeight)
        val spinnerAge = findViewById<Spinner>(R.id.spinnerAge)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val buttonContinue = findViewById<Button>(R.id.buttonContinue)

        buttonContinue.setOnClickListener {
            // Recopila la información del perfil del usuario
            val weight = editTextWeight.text.toString().toDoubleOrNull()
            val age = spinnerAge.selectedItem.toString().toIntOrNull()
            val gender = spinnerGender.selectedItem.toString()
            val height = editTextHeight.text.toString().toDoubleOrNull()

            // Verifica que la información recopilada es válida
            if (weight != null && age != null && height != null) {
                // Crea el objeto UserProfile
                val userProfile = UserProfile(weight, height, gender, age)

                // Usa el ViewModel para guardar la información del perfil del usuario
                viewModel.updateUserProfile(userProfile) { isSuccess ->
                    if (isSuccess) {
                        // Los datos se guardaron correctamente, navega a MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Hubo un error al guardar los datos
                        Log.e("ProfileCustomization", "Error al guardar el perfil del usuario")
                    }
                }
            } else {
                // Alguna de la información del perfil del usuario es inválida
                Log.e("ProfileCustomization", "La información del perfil del usuario es inválida")
            }
        }
    }
}
