package com.example.stormbringersheetmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AccountFragment()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_account)
        }

    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_account ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    AccountFragment()
                ).commit()
            R.id.nav_games ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    GamesFragment()
                ).commit()
            R.id.nav_vault ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    VaultFragment()
                ).commit()
            R.id.nav_register ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    RegisterFragment()
                ).commit()
            R.id.nav_login ->
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    LoginFragment()
                ).commit()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}