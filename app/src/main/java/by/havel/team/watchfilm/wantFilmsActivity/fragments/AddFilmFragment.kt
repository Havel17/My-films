package by.havel.team.watchfilm.wantFilmsActivity.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.havel.team.watchfilm.R
import kotlinx.android.synthetic.main.fragment_addwantfilm.*

class AddFilmFragment : Fragment(), View.OnClickListener {
    
    lateinit var sendfilm: isendFilm
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addwantfilm, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        addwantedfilmfragment.setOnClickListener(this)
        
    }
    
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addwantedfilmfragment -> {
                val ac = activity
                val str1 = addname.text.toString()
                val str2 = addyear.text.toString()
                sendfilm.sendName(str1)
                sendfilm.sendYear(str2)
                sendfilm.addFilm()
                if (ac != null) {
                    ac.supportFragmentManager.beginTransaction().remove(this).commit()
                }
            }
        }
    }
    
    interface isendFilm {
        fun sendName(name: String)
        fun sendYear(year: String)
        fun addFilm()
    }
    
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        sendfilm = activity as isendFilm
        
    }
    
}