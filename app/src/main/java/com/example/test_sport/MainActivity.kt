package com.example.test_sport

import Fragment.HomeFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.custom_login_popup.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav)
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

    }

}