package com.example.test_sport.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.test_sport.R
import com.example.test_sport.splash.splash2
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash2.*
import kotlinx.android.synthetic.main.activity_splash3.*

class splash3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash3)
        supportActionBar?.hide()

         //   val intent = Intent(this@splash3, Login::class.java)

       Handler().postDelayed(Runnable {  intentt() }, 2000)
        }

    private fun intentt() {
        val intent = Intent(this@splash3, com.example.test_sport.Login::class.java)
        startActivity(intent)
    }
}
