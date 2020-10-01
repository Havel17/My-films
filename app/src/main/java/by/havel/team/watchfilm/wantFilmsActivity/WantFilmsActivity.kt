package by.havel.team.watchfilm.wantFilmsActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.havel.team.watchfilm.R
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.repository.LocalRepository
import by.havel.team.watchfilm.viewedFilmsActivity.ViewedFilmsActivity
import by.havel.team.watchfilm.wantFilmsActivity.fragments.AddFilmFragment
import kotlinx.android.synthetic.main.activity_wantfilms.*

class WantFilmsActivity : AppCompatActivity(), View.OnClickListener, WantFilmsAdapter.OnItemClick,
    AddFilmFragment.isendFilm {
    
    lateinit var viewModel: WantFilmsViewModel
    lateinit var wantFilmsAdapter: WantFilmsAdapter
    val film = Film(0, "0", "0", "0", "0", "0", "0", false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wantfilms)
        setSupportActionBar(toolBar)
        
        viewModel = ViewModelProvider(
            this,
            WantFilmViewModelFactory(LocalRepository)
        ).get(WantFilmsViewModel::class.java)
        viewModel.filmsLiveData.observe(this, observer)
        
        viewModel.getAllFilm()
        
        wantFilmsAdapter = WantFilmsAdapter(this)
        wantfilmrecyclerview.layoutManager = LinearLayoutManager(this)
        wantfilmrecyclerview.adapter = wantFilmsAdapter
        
        
        viewedfilms.setOnClickListener(this)
        addwantedfilm.setOnClickListener(this)
        sorting.setOnClickListener(this)
    }
    
    
    override fun onClick(v: View?) {
        when (v?.id) {
    
            R.id.viewedfilms -> {
                openViewedFilmsActivity()
            }
            R.id.addwantedfilm -> {
                addwantedfilm.visibility = GONE
                openAddFilmFragment()
        
            }
            R.id.sorting -> {
                viewModel.removeAllFilm()
                wantFilmsAdapter.films.clear()
                wantFilmsAdapter.notifyDataSetChanged()
            }
        }
    }
    
    
    fun openViewedFilmsActivity() {
        val intent = Intent(this, ViewedFilmsActivity::class.java)
        startActivity(intent)
    }
    
    fun openAddFilmFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.want_film_Fragment_container,
                AddFilmFragment()
            )
            .commit()
    }
    
    private val observer = Observer<MutableList<Film>> {
        wantFilmsAdapter.films.addAll(it)
        wantFilmsAdapter.notifyDataSetChanged()
    }
    
    override fun onItemClicked(position: Int) {
    }

    
    override fun sendName(name: String) {
        film.name = name
    }
    
    override fun sendYear(year: String) {
        film.year = year
    }
    
    override fun addFilm() {
        addwantedfilm.visibility = VISIBLE
        viewModel.insertFilm(film)
        film.id = wantFilmsAdapter.films.size
        wantFilmsAdapter.films.add(film)
        wantFilmsAdapter.notifyDataSetChanged()
    }
    
}

