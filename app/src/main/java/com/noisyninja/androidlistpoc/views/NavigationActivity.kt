package com.noisyninja.androidlistpoc.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.noisyninja.androidlistpoc.R

/**
 * A simple activity demonstrating use of a NavHostFragment with a navigation drawer.
 */
class NavActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.navigation_graph) as NavHostFragment? ?: return

        // Set up Navigation
        navController = host.navController
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)

        setupActionBarWithNavController(navController)
        setupBottomNavMenu(navController)
    }

    private fun setupActionBarWithNavController(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        /*findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.let { bottomNavView ->
            // NavigationUI.setupWithNavController(bottomNavView, navController)
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}