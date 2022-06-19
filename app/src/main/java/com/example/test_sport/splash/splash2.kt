package com.example.test_sport.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_sport.R
import com.example.test_sport.splash.splash2
import kotlinx.android.synthetic.main.activity_splash2.*

class splash2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        supportActionBar?.hide()

        bsplash2.setOnClickListener {   val intent = Intent(this@splash2,splash3::class.java)
            startActivity(intent) }

    }
}