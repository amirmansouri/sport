package Fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test_sport.MySliderImageAdapter
import com.example.test_sport.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class HomeFragment() : Fragment() {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("user")
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        repeat(3) { index ->
            Handler().postDelayed(Runnable { motivText() }, 1000)
        }
        Handler().postDelayed(Runnable { slider() }, 1000)

        Load()

        return view
    }


    private fun slider() {
        val imageSlider = view?.findViewById<SliderView>(R.id.imageSlider)
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://d1csarkz8obe9u.cloudfront.net/posterpreviews/motivation-quotes-sport-fitness-woman-power-design-template-d75d3ff59e7629168b3ba036e55b927c_screen.jpg?ts=1611411372")
        imageList.add("https://communaute.ucpa.com/legacyfs/online/uploads/2017/06/Citation-motivation-sport-talent-650x650.jpg")
        imageList.add("https://i0.wp.com/commencer-le-sport.com/wp-content/uploads/2020/06/10-minutes-de-gainage-2-1.png?resize=1140%2C641&ssl=1")
        imageList.add("https://media.fitnessboutique.fr/images/fr/staticcontents/FBFR_2012_garder-motivation-objectifs.jpg")
        if (imageSlider != null) {
            setImageInSlider(imageList, imageSlider)
        }
    }

    private fun setImageInSlider(images: java.util.ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }

    @SuppressLint("SetTextI18n")
    private fun motivText() {
        val motivation1 = getString(R.string.motivation1)
        val motivation2 = getString(R.string.motivation2)
        motivation.text = motivation1 + motivation2
        motivation.setSingleLine()
        motivation.ellipsize = TextUtils.TruncateAt.MARQUEE
        motivation.marqueeRepeatLimit = -1
        motivation.isSelected = true
    }


    private fun Load() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        //profile_email.text = user?.email
        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                welcome.text = snapshot.child("Name").value.toString()
                val taille = snapshot.child("taille").value.toString()
                val poid = snapshot.child("poid").value.toString()
                val p = poid.toFloat()
                val t1 = taille.toFloat() * 0.01
                ideal.text = ((p / (t1 * t1)).toString())
                if (ideal.text.toString() < 18.5.toString()) {
                    ideal.setTextColor(Color.parseColor("#FFFF00"))
                    resultat.text="insuffisance pondérale"
                    resultat.setTextColor(Color.parseColor("#FFFF00"))
                    maigreur.visibility = View.VISIBLE
                    //  fragment.view?.setBackgroundColor(Color.GREEN)
                    // frame.setBackgroundColor(Color.GREEN)
                    //   frame.colorTransition(R.color.colorPrimary,R.color.white)
                }
                if (ideal.text.toString() < 29.9.toString() && ideal.text.toString() > 18.5.toString()) {
                    normal.visibility = View.VISIBLE
                  ideal.setTextColor(Color.parseColor("#0CB60C"))
                    resultat.text="poids normal"
                    resultat.setTextColor(Color.parseColor("#0CB60C"))
                }
                if (ideal.text.toString() > 29.9.toString()) {
                    ideal.setTextColor(Color.parseColor("#FF0000"))
                    resultat.text="surpoids"
                    resultat.setTextColor(Color.parseColor("#FF0000"))
                    obesite.visibility = View.VISIBLE
                }
                //   Poids idéal Masculin (en Kg) = Taille (en cm) - 100 - ((Taille (en cm) - 150) /4 ).
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

//    fun View.colorTransition(
//        @ColorRes startColor: Int,
//        @ColorRes endColor: Int,
//        duration: Long = 1000
//    ) {
//        val colorFrom = ContextCompat.getColor(context, startColor)
//        val colorTo = ContextCompat.getColor(context, endColor)
//        val colorAnimation: ValueAnimator =
//            ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
//        colorAnimation.duration = duration
//
//        colorAnimation.addUpdateListener {
//            if (it.animatedValue is Int) {
//                val color = it.animatedValue as Int
//                setBackgroundColor(color)
//            }
//        }
//        colorAnimation.start()
//    }


}



