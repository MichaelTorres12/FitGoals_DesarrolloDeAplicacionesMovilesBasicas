package com.fitgoalsappsmoviles.fitgoals.activities

//Activity de bienvenida para que el Usuario decida registrarse o logearse.

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
        val buttonRegister = findViewById<Button>(R.id.btnRegister)

        //Conecta con la vista del login
        val buttonLogin = findViewById<Button>(R.id.btnLogin)

        // Establece un click listener en el botón de Register
        buttonRegister.setOnClickListener {
            // Crea un Intent para iniciar RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Establece un click listener en el botón de Login
        buttonLogin.setOnClickListener {
            // Crea un segundo Intent para iniciar LoginActivity
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
        }

    }
}