package Fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test_sport.CustomLoginPopup2
import com.example.test_sport.R
import com.example.test_sport.R.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.custom_login_popup.*
import kotlinx.android.synthetic.main.custom_login_popup.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_planing.*
import kotlinx.android.synthetic.main.fragment_planing.view.*
import kotlinx.android.synthetic.main.fragment_profile.*


/**
 * A simple [Fragment] subclass.
 * Use the [PlaningFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaningFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    lateinit var database: FirebaseDatabase


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View?  {
             // Inflate the layout for this fragment
       val view =   inflater.inflate(layout.fragment_planing, container, false)
         // in Fragment

//offer_disp.setBackgroundColor(Color.parseColor("#0CB60C"))
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("user")
loadProfile()
        val reservation = view.findViewById<TextView>(R.id.Reservation)
    reservation.setOnClickListener {

    }
view.coach3.setOnClickListener {
    dial()
}
       (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        return view

    }




    private fun dial() {
       val dialog = this.context?.let { CustomLoginPopup2(it, style.Theme_Dialog_Light) }
        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog?.show()
    }
    private fun loadProfile() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        //profile_email.text = user?.email
        userreference.addValueEventListener(object : ValueEventListener {
           override fun onDataChange(snapshot: DataSnapshot) {
                n.text = snapshot.child("votre offer").value.toString()
                if(n.text.equals("platinum")){
                    lundi.setBackgroundColor(Color.parseColor("#D1D1D1"))
                    jeudi.setBackgroundColor(Color.parseColor("#888470"))
                    dimanche.setBackgroundColor(Color.parseColor("#737271"))
                    scroll.setBackgroundColor(Color.parseColor("#E5E4E2"))
                    Toast.makeText(this@PlaningFragment.context, "platinum offre ", Toast.LENGTH_LONG).show()
                    platinum_offre.visibility = View.VISIBLE
                    gold_offre.visibility = View.INVISIBLE
                }else{
                    gold_offre.visibility = View.VISIBLE
                    platinum_offre.visibility = View.INVISIBLE
                        Toast.makeText(this@PlaningFragment.context, "test test ", Toast.LENGTH_LONG).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}