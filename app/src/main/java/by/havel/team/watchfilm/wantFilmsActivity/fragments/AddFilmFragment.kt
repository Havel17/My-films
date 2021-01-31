package by.havel.team.watchfilm.wantFilmsActivity.fragments

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import by.havel.team.watchfilm.R
import kotlinx.android.synthetic.main.fragment_addwantfilm.*
import kotlinx.android.synthetic.main.model_film.*

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
        addname.requestFocus()
        addyear.inputType = InputType.TYPE_CLASS_NUMBER
    }
    
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.addwantedfilmfragment -> {
                getData()
            }
        }
    }
    
    fun getData(){
        val name = addname.text.toString()
        val year = addyear.text.toString()
        isDataEmpty(name,year)
    }
    
    fun isDataEmpty(name: String,year: String) {
        if (name == "") {
            Toast.makeText(context, "Введите название", Toast.LENGTH_SHORT).show()
            addname.requestFocus()
            return
        } else if (year == "") {
            Toast.makeText(context, "Введите год", Toast.LENGTH_SHORT).show()
            addyear.requestFocus()
            return
//        }else if( Integer.parseInt(year) <1800 ){
//            Toast.makeText(context,"это кино снимали динозавры?" , Toast.LENGTH_SHORT).show()
//            return
//        }else if( Integer.parseInt(year) >3000){
//            Toast.makeText(context,"МакФлай привез фильм из будущего?" , Toast.LENGTH_SHORT).show()
//            return
        }
        sendfilm.sendFilm(name, year)
        val ac = activity
        if (ac != null) {
            ac.supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }
    
    // что бы взаимодействовать с активити
    interface isendFilm {
        fun sendFilm(name: String, year: String)
    }
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        sendfilm = activity as isendFilm
    }
}

