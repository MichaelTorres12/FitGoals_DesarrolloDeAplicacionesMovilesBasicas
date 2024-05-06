package com.fitgoalsappsmoviles.fitgoals.activities

// Activity que sirve para registrar nuevos usuarios en la APP, gestionando tanto el registro estándar como el registro a través de Google.

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fitgoalsappsmoviles.fitgoals.R
import com.fitgoalsappsmoviles.fitgoals.viewmodels.UserViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel  // ViewModel que proporciona métodos para el registro de usuarios.
    private lateinit var googleSignInClient: GoogleSignInClient  // Cliente de autenticación de Google.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicialización de UserViewModel.
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        // Referencias a los componentes de UI en el layout.
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val googleSignInButton = findViewById<SignInButton>(R.id.sign_in_google_button)

        // Configuración del cliente de Google Sign-In con opciones específicas para solicitar token ID y email.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Establece un listener para el botón de registro tradicional.
        buttonRegister.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Verifica que los campos no estén vacíos antes de proceder al registro.
            if(email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.registerUser(email, password) { isSuccess ->
                    if(isSuccess) {
                        // Navegación a ProfileCustomizationActivity si el registro es exitoso.
                        val intent = Intent(this, ProfileCustomizationActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Mensaje de error si el registro falla.
                        Toast.makeText(this, "Ha habido un error al registrar el usuario, inténtalo de nuevo", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Mensaje de error si los campos están vacíos.
                Toast.makeText(this, "Los campos de Correo y Contraseña deben de estar llenados", Toast.LENGTH_SHORT).show()
            }
        }

        // Establece un listener para el botón de registro con Google.
        googleSignInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    // Gestiona el resultado del intento de inicio de sesión con Google.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    // Maneja el resultado del inicio de sesión con Google, autenticando con Firebase si es exitoso.
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Autenticación con Firebase usando el token ID de Google.
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            // Log y manejo de errores si el inicio de sesión con Google falla.
        }
    }

    // Autentica al usuario con Firebase usando el token de Google y maneja el éxito o fallo.
    private fun firebaseAuthWithGoogle(idToken: String) {
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Navegación a ProfileCustomizationActivity si la autenticación es exitosa.
                    val intent = Intent(this, ProfileCustomizationActivity::class.java)
                    startActivity(intent)
                } else {
                    // Manejo de fallos en la autenticación.
                }
            }
    }

    companion object {
        private const val RC_SIGN_IN = 9001  // Código de solicitud para el inicio de sesión con Google.
    }
}
