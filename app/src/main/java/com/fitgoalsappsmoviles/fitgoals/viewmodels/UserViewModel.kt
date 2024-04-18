package com.fitgoalsappsmoviles.fitgoals.viewmodels

/*
* UserViewModel es donde manejas los datos para la UI, siguiendo los principios del patrón
* MVVM (Model-View-ViewModel). El ViewModel actúa como intermediario entre la vista (actividades o fragmentos)
* y el modelo (en este caso, servicios y repositorios), manteniendo la lógica de la UI separada de la lógica del negocio.*/

import androidx.lifecycle.ViewModel
import com.fitgoalsappsmoviles.fitgoals.repositories.UserRepository
import com.fitgoalsappsmoviles.fitgoals.services.AuthenticationService

class UserViewModel : ViewModel() {

    private val authService = AuthenticationService(UserRepository())

    //Para poder registrar usuarios nuevos
    fun registerUser(email: String, password: String, callback: (Boolean) -> Unit) {
        authService.registerUser(email, password, callback)
    }


    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        authService.loginUser(email, password, callback)
    }
}
