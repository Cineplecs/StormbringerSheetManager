package com.example.stormbringersheetmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.mainInfoCharacterFragment,
                R.id.gamesFragment, R.id.accountFragment,
                R.id.registerFragment, R.id.loginFragment,
                R.id.vaultFragment, R.id.skillsCalculatorAndSelection,
                R.id.classSelection, R.id.equipmentSelection
            ),
            drawer
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                else -> {
                    if (navController.currentDestination?.label.toString().contains("Main") ||
                        navController.currentDestination?.label.toString().contains("Equipment") ||
                        navController.currentDestination?.label.toString().contains("Skill") ||
                        navController.currentDestination?.label.toString().contains("Class")
                    ) {
                        showDialog(menuItem)
                    } else {
                        NavigationUI.onNavDestinationSelected(menuItem, navController)
                        drawer.closeDrawers()
                    }
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.homeFragment) {
            println(navController.currentDestination?.label.toString())
            return if (navController.currentDestination?.label.toString().contains("Main") ||
                navController.currentDestination?.label.toString().contains("Equipment") ||
                navController.currentDestination?.label.toString().contains("Skill") ||
                navController.currentDestination?.label.toString().contains("Class")
            ) {
                println("Qui ci siamo")
                showDialog()
                true
            } else {
                return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(
                    item
                )
            }
        } else {
            return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(
                item
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {

    }

    private fun showDialog(menuItem: MenuItem) {
        val alertDialog = AlertDialog.Builder(this).apply {
            setTitle("Vuoi tornare indietro?")
            setMessage("Ogni modifica non salvata andrà persa")
            setPositiveButton("Sì") { _, _ ->
                Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
                    .navigate(menuItem.itemId)
                drawer.closeDrawers()
            }
            setNegativeButton("No") { _, _ ->
                drawer.closeDrawers()
            }
        }.show()
    }

    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(this).apply {
            setTitle("Vuoi tornare indietro?")
            setMessage("Ogni modifica non salvata andrà persa")
            setPositiveButton("Sì") { _, _ ->
                Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment)
                    .navigate(R.id.homeFragment)
                drawer.closeDrawers()
            }
            setNegativeButton("No") { _, _ ->

            }
        }.show()


    }
}