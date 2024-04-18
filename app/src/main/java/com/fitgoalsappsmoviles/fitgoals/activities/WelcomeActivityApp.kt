package com.fitgoalsappsmoviles.fitgoals.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.fitgoalsappsmoviles.fitgoals.R

class WelcomeActivityApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Encuentra el botón por su ID
        // Conectar con la vista del register
        val buttonRegister = findViewById<Button>(R.id.btnRegister)

        //Conectar con la vista del login
        val buttonLogin = findViewById<Button>(R.id.btnLogin)

        // Establece un click listener en el botón
        buttonRegister.setOnClickListener {
            // Crea un Intent para iniciar RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Establece un click listener en el botón
        buttonLogin.setOnClickListener {
            // Crea un Intent para iniciar LoginActivity
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
        }

    }
}