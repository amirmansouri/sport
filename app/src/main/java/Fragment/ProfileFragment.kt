package Fragment

import android.R.string.ok
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.example.test_sport.Login
import com.example.test_sport.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {
    lateinit var img: ImageView
    lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("user")
/******/

showHide(view)
        /****/



        img()
        loadProfile()
        view.out.setOnClickListener {
            auth.signOut()
            val intent = Intent(this@ProfileFragment.context, Login::class.java)
            startActivity(intent)
        }
        return view
    }


    private fun img() {
        val image = view?.findViewById<ImageView>(R.id.photo_profile)
     image?.setImageResource(R.drawable.circle)
    }
    fun showHide(view:View) {
        view.photo_profile.visibility= View.VISIBLE
    }
    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        //profile_email.text = user?.email
        userreference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                profile_name.text = snapshot.child("Name").value.toString()
                profile_email.text = snapshot.child("Email").value.toString()
                profile_num.text = snapshot.child("Phone").value.toString()
                poid.text=snapshot.child("poid").value.toString()
                taille.text=snapshot.child("taille").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}


