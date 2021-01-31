package by.havel.team.watchfilm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.havel.team.watchfilm.wantFilmsActivity.fragments.FilmMenuFragment
import kotlinx.android.synthetic.main.fragment_mainmenu.*

class MainMenuFragment : Fragment(), View.OnClickListener {
    
    lateinit var mainmenufilm: imainmenuFilm
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mainmenu,container,false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findfilm.setOnClickListener(this)
        update.setOnClickListener(this)
    }
    interface imainmenuFilm {
        fun findfilm(findWord:String)
        fun update()
    }
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainmenufilm = activity as imainmenuFilm
    }
        override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.findfilm ->{
                mainmenufilm.findfilm(findtext.text.toString())
            }
            R.id.update->{
                mainmenufilm.update()
                closeFragment()
            }
        }
    }
    
    fun closeFragment(){
        val ac = activity
        if (ac != null) {
            ac.supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}