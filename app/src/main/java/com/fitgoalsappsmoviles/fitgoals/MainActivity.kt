package com.fitgoalsappsmoviles.fitgoals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.fitgoalsappsmoviles.fitgoals.fragments.HomeFragment
import com.fitgoalsappsmoviles.fitgoals.fragments.ProfileFragment

// ... otros imports

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment() // Asegúrate de tener este Fragment.

        setCurrentFragment(homeFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> setCurrentFragment(homeFragment)
                R.id.navigation_profile -> setCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, fragment)
            commit()
        }
}