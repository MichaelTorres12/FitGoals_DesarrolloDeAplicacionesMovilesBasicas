package com.fitgoalsappsmoviles.fitgoals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.fitgoalsappsmoviles.fitgoals.fragments.HomeFragment
import com.fitgoalsappsmoviles.fitgoals.fragments.ProfileFragment

// MainActivity organiza la navegación principal en la aplicación utilizando un BottomNavigationView.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Establece el layout que contiene el BottomNavigationView.

        // Inicialización de fragmentos.
        val homeFragment = HomeFragment()  // Fragmento de la pantalla principal.
        val profileFragment = ProfileFragment()  // Fragmento de la pantalla de perfil del usuario.

        // Establece el fragmento inicial mostrado en la actividad.
        setCurrentFragment(homeFragment)

        // Configuración del BottomNavigationView.
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> setCurrentFragment(homeFragment)  // Muestra HomeFragment.
                R.id.navigation_profile -> setCurrentFragment(profileFragment)  // Muestra ProfileFragment.
            }
            true  // Retorna true para mostrar que el evento de selección de menú ha sido manejado.
        }
    }

    // Función para cambiar el fragmento actual en el contenedor de la actividad principal.
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, fragment)  // Reemplaza el fragmento actual por el nuevo.
            commit()  // Confirma la transacción.
        }
}
