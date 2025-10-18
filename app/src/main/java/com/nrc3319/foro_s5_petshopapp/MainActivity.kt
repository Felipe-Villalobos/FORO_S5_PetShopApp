// ruta: app/src/main/java/com/nrc3319/foro_s5_petshopapp/MainActivity.kt
package com.nrc3319.foro_s5_petshopapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // Declaramos las vistas que vamos a controlar
    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Encontrar la barra de navegación en nuestro layout (activity_main.xml)
        bottomNavView = findViewById(R.id.bottom_nav_view)

        // 2. Encontrar el controlador de navegación.
        //    Este es el cerebro que gestiona qué fragmento se muestra.
        //    Lo obtenemos a través del NavHostFragment que definimos en el XML.
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 3. ¡La Magia! Esta única línea conecta la barra de navegación con el controlador.
        //    Ahora, al pulsar un botón en la barra (ej. "Galería"), el NavController
        //    automáticamente mostrará el fragmento correcto (GalleryFragment).
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }
}
