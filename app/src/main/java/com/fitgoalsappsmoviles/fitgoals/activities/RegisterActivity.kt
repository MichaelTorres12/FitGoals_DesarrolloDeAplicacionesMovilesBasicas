package com.fitgoalsappsmoviles.fitgoals.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

    private lateinit var viewModel: UserViewModel
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val googleSignInButton = findViewById<SignInButton>(R.id.sign_in_google_button) // Asegúrate de que el ID es correcto

        // Configura Google Sign-In Client
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Listener para el botón de registro con correo electrónico y contraseña
        buttonRegister.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.registerUser(email, password) { isSuccess ->
                    if(isSuccess) {
                        // Usuario registrado exitosamente
                        // Navegar a la siguiente pantalla
                        val intent = Intent(this, ProfileCustomizationActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Manejar el error de registro
                    }
                }
            } else {
                // Indicar al usuario que los campos no deben estar vacíos
            }
        }

        // Listener para el botón de inicio de sesión con Google
        googleSignInButton.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
// Log the error or handle the sign in failure
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Here you can either navigate to the next screen, or do some work with the FirebaseUser
                    // FirebaseUser user = task.getResult().getUser();
                    // Update UI with the user information or store user data in the database
                    // Navegar a la siguiente pantalla cuando los datos digitados son guardado en la DB

                    // Establece un click listener en el botón
                    buttonRegister.setOnClickListener {
                        // Crea un Intent para iniciar ProfileCustomizationActivity
                        val intent = Intent(this, ProfileCustomizationActivity::class.java)
                        startActivity(intent)
                }
                }
                else {
                    // If sign in fails, display a message to the user.
                }
            }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }
}

