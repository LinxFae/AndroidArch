package com.noisyninja.androidlistpoc.views

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.noisyninja.androidlistpoc.R

/**
 * A simple activity demonstrating use of a NavHostFragment with a navigation drawer.
 */
class NavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.navigation_graph) as NavHostFragment? ?: return

        // Set up Navigation
        val navController = host.navController

        setupBottomNavMenu(navController)

    }

    private fun setupBottomNavMenu(navController: NavController) {
        findViewById<BottomNavigationView>(R.id.bottom_nav_view)?.let { bottomNavView ->
            NavigationUI.setupWithNavController(bottomNavView, navController)
        }
    }

}