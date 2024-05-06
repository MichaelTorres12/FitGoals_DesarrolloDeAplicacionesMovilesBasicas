package com.fitgoalsappsmoviles.fitgoals.activities

// Activity utilizada para mostrar los créditos de los desarrolladores, el docente y la materia relacionada.

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fitgoalsappsmoviles.fitgoals.R

class CreditosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creditos_activity)  // Asigna el layout de la actividad de créditos.

        // Inicialización del botón de regreso.
        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()  // Cierra la actividad y regresa a la pantalla anterior al presionar el botón.
        }
    }
}
