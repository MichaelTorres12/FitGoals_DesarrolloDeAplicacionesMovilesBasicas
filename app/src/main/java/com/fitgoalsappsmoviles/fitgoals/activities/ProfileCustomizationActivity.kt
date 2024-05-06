package com.fitgoalsappsmoviles.fitgoals.activities

/*Activity donde el usuario puede personalizar su cuenta con parámetros como estatura, edad,
peso y sexo. Esta pantalla es generalmente accesible después de que el usuario se registra.*/

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fitgoalsappsmoviles.fitgoals.MainActivity
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.models.UserProfile
import com.fitgoalsappsmoviles.fitgoals.viewmodels.ProfileViewModel

class ProfileCustomizationActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel  // ViewModel que maneja la lógica del perfil de usuario.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_customization)  // Establece el layout para la actividad de personalización del perfil.

        // Inicialización del ViewModel.
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        // Inicialización de los componentes de la UI.
        val editTextWeight = findViewById<EditText>(R.id.editTextCurrentWeight)
        val spinnerAge = findViewById<Spinner>(R.id.spinnerAge)
        val spinnerGender = findViewById<Spinner>(R.id.spinnerGender)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val buttonContinue = findViewById<Button>(R.id.buttonContinue)

        // Establece un listener para el botón 'Continuar'.
        buttonContinue.setOnClickListener {
            // Recopila la información del perfil del usuario desde la interfaz.
            val weight = editTextWeight.text.toString().toDoubleOrNull()  // Convierte el texto a Double, retorna null si no es posible.
            val age = spinnerAge.selectedItem.toString().toIntOrNull()  // Convierte el texto a Int, retorna null si no es posible.
            val gender = spinnerGender.selectedItem.toString()  // Obtiene el género seleccionado.
            val height = editTextHeight.text.toString().toDoubleOrNull()  // Convierte el texto a Double, retorna null si no es posible.

            // Verifica que la información recopilada es válida.
            if (weight != null && age != null && height != null) {
                // Crea el objeto UserProfile con los datos recopilados.
                val userProfile = UserProfile(weight, height, gender, age)

                // Usa el ViewModel para guardar la información del perfil del usuario.
                viewModel.updateUserProfile(userProfile) { isSuccess ->
                    if (isSuccess) {
                        // Los datos se guardaron correctamente, navega a MainActivity.
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()  // Finaliza esta actividad para no volver a ella al presionar 'atrás' desde MainActivity.
                    } else {
                        // Hubo un error al guardar los datos.
                        Log.e("ProfileCustomization", "Error al guardar el perfil del usuario")
                        Toast.makeText(this, "Error al guardar el perfil del usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Alguna de la información del perfil del usuario es inválida.
                Log.e("ProfileCustomization", "La información del perfil del usuario es inválida")
                Toast.makeText(this, "Debes rellenar todos los campos con información válida", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
