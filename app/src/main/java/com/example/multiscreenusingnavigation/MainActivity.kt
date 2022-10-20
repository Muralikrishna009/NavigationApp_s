package com.example.multiscreenusingnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView =
            findViewById<com.google.android.material.navigation.NavigationView>(R.id.navView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        changeFragment(HomeFragment(), "Home")

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> changeFragment(HomeFragment(), it.title.toString())
                R.id.first -> changeFragment(FirstFragment(), it.title.toString())
                R.id.second -> changeFragment(SecondFragment(), it.title.toString())

            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentHolder, fragment)
        fragmentTransaction.commit()
        drawerLayout.close()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}