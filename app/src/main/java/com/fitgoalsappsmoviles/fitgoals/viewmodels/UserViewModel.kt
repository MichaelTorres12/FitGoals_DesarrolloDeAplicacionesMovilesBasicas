package com.fitgoalsappsmoviles.fitgoals.viewmodels

/*
* UserViewModel es donde se manejan los datos para la UI siguiendo los principios del patrón MVVM (Model-View-ViewModel).
* Este ViewModel actúa como un puente entre la vista (actividades o fragmentos) y el modelo (servicios y repositorios),
* manteniendo así la lógica de la interfaz de usuario separada de la lógica del negocio.
* Facilita la gestión de estados de la UI y manipulación de los datos de usuario de manera más eficiente y organizada.
*/

import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository
import com.fitgoalsappsmoviles.fitgoals.services.AuthenticationService

class UserViewModel : ViewModel() {
    // Instancia de AuthenticationService que utiliza UserRepository para operaciones relacionadas con la autenticación.
    private val authService = AuthenticationService(UserRepository())

    // Método para registrar usuarios nuevos. Este método que toma un email y contraseña, y un callback para manejar la respuesta.
    // Utiliza authenticationService para delegar la acción de registro. El callback es llamado con 'true' si el registro es exitoso, y 'false' si falla.
    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        authService.registerUser(email, password, callback)
    }

    // Método para iniciar sesión de usuarios. Al igual que el método de registro, toma un email y contraseña, y un callback.
    // Este método permite a los usuarios acceder a sus cuentas, utilizando authService para validar las credenciales.
    // El callback informa si el inicio de sesión fue exitoso ('true') o no ('false').
    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        authService.loginUser(email, password, callback)
    }
}
