package com.example.keepplaying.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.keepplaying.R
import com.example.keepplaying.databinding.ActivityMenuMainBinding
import com.example.keepplaying.presentation.home_menu.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fMain) as NavHostFragment?
        val navController = navHostFragment?.navController

        // Obtén los argumentos del Intent
        val uid = intent.getStringExtra("uid")
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")

        // Verifica si los argumentos están presentes antes de pasarlos
        if (uid != null && nombre != null && apellido != null) {
            // Aquí pasas los argumentos al HomeFragment
            val bundle = Bundle().apply {
                putString("uid", uid)
                putString("nombre", nombre)
                putString("apellido", apellido)
            }
            // Encuentra el HomeFragment en el NavHostFragment y configura los argumentos
            val homeFragment = navHostFragment?.childFragmentManager?.fragments?.find { it is HomeFragment } as HomeFragment?
            homeFragment?.arguments = bundle
        }

        binding.bnvMainMenu.apply {
            navController?.let { navController ->
                NavigationUI.setupWithNavController(
                    this,
                    navController
                )
                setOnItemSelectedListener { item ->
                    NavigationUI.onNavDestinationSelected(item, navController)
                    true
                }
                setOnItemReselectedListener {
                    val selectedMenuItemNavGraph =
                        navController.graph.findNode(it.itemId) as NavGraph
                    selectedMenuItemNavGraph.let { menuGraph ->
                        navController.popBackStack(menuGraph.startDestinationId, false)
                    }
                }
            }
        }
    }
}
