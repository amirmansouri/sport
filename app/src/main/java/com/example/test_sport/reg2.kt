package com.example.test_sport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.custom_login_popup.*
import kotlinx.android.synthetic.main.custom_login_popup.view.*
import kotlinx.android.synthetic.main.reg2.*
import kotlinx.android.synthetic.main.reg2.view.*

class reg2 : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reg2)
        val send = findViewById<Button>(R.id.send)


        send.setOnClickListener {
            register()
        }
    }


    private fun register() {
        val EmailRegister = intent.getStringExtra("EmailRegister")
        val Name = intent.getStringExtra("Name")
        val Phone = intent.getStringExtra("Phone")
        val PasswordRegister = intent.getStringExtra("PasswordRegister")


        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("user")
        auth.createUserWithEmailAndPassword(EmailRegister.toString(), PasswordRegister.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUSerDatabase = databaseReference.child((currentUser?.uid!!)).apply {

                        Toast.makeText(this@reg2, "$EmailRegister. ", Toast.LENGTH_LONG).show()
                        child("Email").setValue(EmailRegister)
                        child("Name").setValue(Name)
                        child("Phone").setValue(Phone)
                        child("poid").setValue(kg.text.toString())
                        child("taille").setValue(cm.text.toString())
                        //data radio group 1
                        if (radioGroup1.Masculin.isChecked) {
                            child("sexe").setValue("Masculin")
                        } else {
                            child("sexe").setValue("Féminin")
                        }
                        //data radio group 2
                        if (radioGroup2.Gracile.isChecked) {
                            child("votre type de morphologie").setValue("Gracile")
                        }
                        if (radioGroup2.Normale.isChecked)
                            run {
                                child("votre type de morphologie").setValue("Normal")
                            } else {
                            child("votre type de morphologie").setValue("Large")
                        }
                        if (radioGroup3.offer1.isChecked) {
                            child("votre offer").setValue("gold")
                        } else {
                            child("votre offer").setValue("platinum")
                        }


                    }
                    //  currentUSerDatabase.child("phone").setValue(Phone.text.toString())


                    Toast.makeText(this, "Registration Success. ", Toast.LENGTH_LONG).show()
                    //finish()
                    val intent = Intent(this@reg2, Login::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Registration  la Success la z. ", Toast.LENGTH_LONG)
                        .show()
                    //finish()
                }
            }


    }


}



