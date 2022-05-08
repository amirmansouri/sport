package Fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test_sport.R
import kotlinx.android.synthetic.main.fragment_article.view.*


class ArticleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
   view.article1.setOnClickListener{

       lienarticle1()
   }
    view.article2.setOnClickListener {
lienarticle2()
    }
        return view
    }

    private fun lienarticle1() {
        val url = "https://www.lemonde.fr/athletisme/"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(intent)
    }

    private fun lienarticle2() {
        val url = "https://www.eufic.org/fr/que-contient-la-nourriture/article/proteines-qu-est-ce-que-c-est-et-quelle-est-leur-fonction-dans-l-organisme/"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(intent)
        }
    }


