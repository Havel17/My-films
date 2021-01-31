package by.havel.team.watchfilm.wantFilmsActivity.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.havel.team.watchfilm.R
import kotlinx.android.synthetic.main.fragment_filmmenu.*

class FilmMenuFragment(position: Int): Fragment(),View.OnClickListener{
    val pos = position
    lateinit var menuFilm: imenuFilm
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filmmenu,container,false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        deletefilm.setOnClickListener(this)
        changefilm.setOnClickListener(this)
    }
    interface imenuFilm {
        fun removefilm(position: Int)
        fun changeFilm(position: Int)
    }
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        menuFilm = activity as imenuFilm
    }
    
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.deletefilm->{
                menuFilm.removefilm(pos)
                closeFragment()
            }
            R.id.changefilm->{
                menuFilm.changeFilm(pos)
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