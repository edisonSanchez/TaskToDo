package com.example.tasktodo.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktodo.R
import com.example.tasktodo.data.AppPreferences
import com.example.tasktodo.ui.viewmodel.MainViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private val mainFragment = MainFragment()
    private val calendarFragment = CalendarFragment()
    private lateinit var fragmentContainer: FrameLayout
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        fragmentContainer = findViewById(R.id.contentFragment)

        AppPreferences.init(this)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.clear_blue))
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().add(R.id.contentFragment, mainFragment).commitNow()
        supportFragmentManager.beginTransaction().add(R.id.contentFragment, calendarFragment).commitNow()
        supportFragmentManager.beginTransaction().show(mainFragment).commitNow()
        supportFragmentManager.beginTransaction().hide(calendarFragment).commitNow()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId){
            R.id.main -> {
                supportFragmentManager.beginTransaction().show(mainFragment).commitNow()
                supportFragmentManager.beginTransaction().hide(calendarFragment).commitNow()
            }
            R.id.calendar -> {
                supportFragmentManager.beginTransaction().hide(mainFragment).commitNow()
                supportFragmentManager.beginTransaction().show(calendarFragment).commitNow()
            }
            R.id.logout_session -> {
                AppPreferences.removeData()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}