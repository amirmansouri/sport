package com.example.test_sport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.custom_login_popup.*


class Register : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        val dialog = CustomLoginPopup(this, R.style.Theme_Dialog_Light)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        val buttonShow = findViewById<Button>(R.id.register_compte)

        buttonShow.setOnClickListener {
            //  dialog.show()
            val intent = Intent(this, reg2::class.java)
            intent.putExtra("EmailRegister", EmailRegister.text.toString())
            intent.putExtra("Name", Name.text.toString())
            intent.putExtra("Phone", Phone.text.toString())
            intent.putExtra("PasswordRegister", PasswordRegister.text.toString())
            startActivity(intent)
        }



    }

    private fun checking(): Boolean {
        if (Name.text.toString().trim { it <= ' ' }.isNotEmpty()
            && Phone.text.toString().trim { it <= ' ' }.isNotEmpty()
            && EmailRegister.text.toString().trim { it <= ' ' }.isNotEmpty()
            && PasswordRegister.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }

    private fun register() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("user")
        auth.createUserWithEmailAndPassword(
            EmailRegister.text.toString(),
            PasswordRegister.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUSerDatabase = databaseReference.child((currentUser?.uid!!)).apply {

                        child("email").setValue(EmailRegister.text.toString())
                        child("name").setValue(Name.text.toString())
                        child("phone").setValue(Phone.text.toString())
                        //  child("taille").setValue(cm.text.toString())
                    }

                    Toast.makeText(this, "Registration Success. ", Toast.LENGTH_LONG).show()
                    finish()

                } else {
                    Toast.makeText(
                        this,
                        "Registration failed, please try again! ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

}
