package com.fitgoalsappsmoviles.fitgoals.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fitgoalsappsmoviles.fitgoals.R
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fitgoalsappsmoviles.fitgoals.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.fitgoalsappsmoviles.fitgoals.viewmodels.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val googleSignInButton = findViewById<SignInButton>(R.id.sign_in_google_button)

        // Configura Google Sign-In Client
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        buttonLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if(email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginUser(email, password) { isSuccess ->
                    if(isSuccess) {
                        // Inicio de sesión exitoso, navegar a ProfileCustomizationActivity
                        navigateToProfileCustomization()
                    } else {
                        // Error de inicio de sesión, mostrar mensaje al usuario
                        Toast.makeText(this, "El correo o la contraseña no coinciden", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Los campos están vacíos, mostrar mensaje al usuario
                Toast.makeText(this, "Los campos de Correo y Contraseña deben de estar llenados", Toast.LENGTH_SHORT).show()
            }
        }

        googleSignInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    //Funcion de navegar a la siguiente vista luego de darle al boton
    private fun navigateToProfileCustomization() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val RC_SIGN_IN = 1001
    }
}