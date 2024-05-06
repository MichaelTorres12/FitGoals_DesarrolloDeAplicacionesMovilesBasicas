package com.fitgoalsappsmoviles.fitgoals.activities

// Importación de componentes necesarios de Android y Firebase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fitgoalsappsmoviles.fitgoals.MainActivity
import com.fitgoalsappsmoviles.fitgoals.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.fitgoalsappsmoviles.fitgoals.viewmodels.UserViewModel

// LoginActivity gestiona el inicio de sesión del usuario.
class LoginActivity : AppCompatActivity() {
    // ViewModel y GoogleSignInClient son inicializados aquí.
    private lateinit var viewModel: UserViewModel
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) //El layout que le pertenece a esta activity

        // Se obtiene la instancia del UserViewModel.
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val googleSignInButton = findViewById<SignInButton>(R.id.sign_in_google_button)

        // Configuración del cliente de Google Sign-In para autenticación.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Listener para el botón de inicio de sesión con email y contraseña.
        buttonLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if(email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginUser(email, password) { isSuccess ->
                    if(isSuccess) {
                        navigateToProfileCustomization()
                        Toast.makeText(this, "Registro con Google Exitoso. Dar click al boton de registrar sesión", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "El correo o la contraseña no coinciden", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Los campos de Correo y Contraseña deben de estar llenados", Toast.LENGTH_SHORT).show()
            }
        }

        // Listener para el botón de inicio de sesión con Google.
        googleSignInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    // Método para navegar a la actividad principal después del inicio de sesión.
    private fun navigateToProfileCustomization() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val RC_SIGN_IN = 1001 // Código de solicitud para el inicio de sesión con Google.
    }
}
