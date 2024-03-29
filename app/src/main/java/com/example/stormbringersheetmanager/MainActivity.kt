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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationView: NavigationView
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = Firebase.auth

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
                R.id.classSelection, R.id.equipmentSelection,
                R.id.sheetFragment
            ),
            drawer
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.logout -> {
                    logout()
                }
                else -> {
                    if (navController.currentDestination?.label.toString().contains("Main") ||
                        navController.currentDestination?.label.toString().contains("Equipment") ||
                        navController.currentDestination?.label.toString().contains("Skill") ||
                        navController.currentDestination?.label.toString().contains("Class")
                    ) {
                        println(menuItem.itemId.toString() + ", ho scelto questo")
                        showDialog(menuItem)
                    } else {
                        NavigationUI.onNavDestinationSelected(menuItem, navController)
                        drawer.closeDrawers()
                    }
                }
            }
            true
        }
        navigationView.menu.findItem(R.id.logout).isVisible = mAuth.currentUser != null
    }

    private fun logout() {
        if(mAuth.currentUser != null){
            mAuth.signOut()
            navController.navigate(R.id.accountFragment)
            drawer.closeDrawers()
            navigationView.menu.findItem(R.id.logout).isVisible = false
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
                navController.currentDestination?.label.toString().contains("Class") ||
                navController.currentDestination?.label.toString().contains("Sheet")
            ) {
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

    override fun onDestroy() {
        if (mAuth.currentUser != null) {
            Firebase.auth.signOut()
        }
        super.onDestroy()
    }
}