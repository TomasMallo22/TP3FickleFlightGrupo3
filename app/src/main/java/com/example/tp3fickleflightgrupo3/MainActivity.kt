package com.example.tp3fickleflightgrupo3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.tp3fickleflightgrupo3.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val fragmentsNavigation = setOf(
        R.id.action_explore,
        R.id.flightSearchFragment,
        R.id.action_offers,
        R.id.profileFragment
    )

    private lateinit var binding: ActivityMainBinding

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.contentMainInclude.customToolbar)

        // Obtener el NavController desde el NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        // Inflar el gráfico de navegación
        navController.setGraph(R.navigation.nav_graph)

        navView = binding.navView
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = fragmentsNavigation,
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        //Toolbar config
        binding.contentMainInclude.customToolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )
        navView.setupWithNavController(navController)

        //NavigationView
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
//        binding.navigationView.setupWithNavController(navController)

        //Comportamiento de fragmentos
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            when (destination.id) {
                R.id.action_explore,
                R.id.flightSearchFragment,
                R.id.action_offers,
                R.id.profileFragment
                -> {
                    navView.visibility = BottomNavigationView.VISIBLE
                    binding.contentMainInclude.customToolbar.visibility = MaterialToolbar.VISIBLE
                }
                else -> {
                    navView.visibility = BottomNavigationView.GONE
                    binding.contentMainInclude.customToolbar.visibility = View.GONE
                }
            }
        }
//        esto no se si va
//        binding.txtCloseApp.setOnClickListener(View.OnClickListener {
//            finish()
//        })

//        Carga modo oscuro/claro
//        loadPreferences()

    }

    override fun onSupportNavigateUp(): Boolean {
        //Fuezo al boton de navegación de la toolbar que solo abra el menú Drawer
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true;
    }

//    Modo oscuro
//    private fun loadPreferences() {
//        val prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        if (prefs.getBoolean(getString(R.string.preference_night_mode_key), false)) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//    }
}
