package com.example.test_sport

import Fragment.HomeFragment
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.test_sport.Login
import com.google.firebase.auth.FirebaseAuth
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.custom_view_register.*

import java.util.*

class Login : AppCompatActivity() {

    // private val mAuth = FirebaseAuth.getInstance()
    var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (FirebaseAuth.getInstance().currentUser!= null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        supportActionBar?.hide()
facebook.setOnClickListener {
    facebook_link()
}
        instagram.setOnClickListener {
            instagram_link()
        }
//
//        val imageSlider = findViewById<SliderView>(R.id.imageSlider)
//        val imageList: ArrayList<String> = ArrayList()
//        imageList.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
//        imageList.add("https://images.ctfassets.net/hrltx12pl8hq/4plHDVeTkWuFMihxQnzBSb/aea2f06d675c3d710d095306e377382f/shutterstock_554314555_copy.jpg")
//        imageList.add("https://media.istockphoto.com/photos/child-hands-formig-heart-shape-picture-id951945718?k=6&m=951945718&s=612x612&w=0&h=ih-N7RytxrTfhDyvyTQCA5q5xKoJToKSYgdsJ_mHrv0=")
//        setImageInSlider(imageList, imageSlider)
nouveau_abonne.setOnClickListener {
//    val intent = Intent(this@Login,Register::class.java)
//    startActivity(intent)
    val inflater = layoutInflater
    val inflate_view = inflater.inflate(R.layout.custom_view_register,null)
    val username = inflate_view.findViewById<EditText>(R.id.name_admin)
    val password = inflate_view.findViewById<EditText>(R.id.password_admin)
    val alertDialog = AlertDialog.Builder(this)
    alertDialog.setTitle("Admin NETVOR")
    alertDialog.setView(inflate_view)
    alertDialog.setCancelable(false)
    alertDialog.setNegativeButton("cancel"){
            _, _ ->
       // Toast.makeText(this,"jklm",Toast.LENGTH_LONG).show()

    }
    alertDialog.setPositiveButton("done"){_,_ ->

        val username = username.text.toString()
        val password = password.text.toString()
       //
        if(username.equals("amir") && password.equals("amir") ){
            val intent = Intent(this@Login,Register::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this@Login, "try again", Toast.LENGTH_LONG).show()
        }

    }
    val dialog = alertDialog.create()
    dialog.show()


}

        Login.setOnClickListener {
            if (checking()) {
                val email = Email.text.toString()
                val password = Password.text.toString()

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                          // val wrong = getString(R.string.wrong_data)
                            Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
               // val detail = getString(R.string.Enter_the_Details)
                Toast.makeText(this, "detail", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun instagram_link() {
            val url = "https://www.instagram.com/mansouri___amir/?hl=fr"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this.startActivity(intent)

    }

    private fun facebook_link() {
            val url = "https://www.facebook.com/yesyesyesyes.y/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this.startActivity(intent)

    }


    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }

    private fun checking(): Boolean {
        if (Email.text.toString().trim { it <= ' ' }.isNotEmpty()
            && Password.text.toString().trim { it <= ' ' }.isNotEmpty()

        ) {
            return true
        }
        return false
    }

    private fun showlang() {
        val listlangue = arrayOf("عربي")
        val mBuilder = AlertDialog.Builder(this@Login)
        mBuilder.setTitle("chose Lang")
        mBuilder.setSingleChoiceItems(listlangue, -1) { dialog, which
            ->
            if (which == 0) {
                setLocate("ar")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }
//    override fun onResume() {
//        auth = FirebaseAuth.getInstance()
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            val i = Intent(this,MainActivity::class.java)
//            startActivity(i)
//        }
//        super.onResume()
//    }
}