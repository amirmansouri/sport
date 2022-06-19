package com.example.test_sport.splash

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import com.example.test_sport.R
import com.example.test_sport.splash.splash2
import kotlinx.android.synthetic.main.activity_splash1.*
import kotlinx.android.synthetic.main.activity_splash2.*

class splash1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash1)
        supportActionBar?.hide()

        bsplash1.setOnClickListener {
            val intent = Intent(this@splash1, splash2::class.java)
            startActivity(intent)
        }


    }
}