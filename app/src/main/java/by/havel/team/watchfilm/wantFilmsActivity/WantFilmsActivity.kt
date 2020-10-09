package by.havel.team.watchfilm.wantFilmsActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.havel.team.watchfilm.R
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.repository.LocalRepository
import by.havel.team.watchfilm.viewedFilmsActivity.ViewedFilmsActivity
import by.havel.team.watchfilm.wantFilmsActivity.fragments.AddFilmFragment
import kotlinx.android.synthetic.main.activity_want_films.*
import kotlinx.android.synthetic.main.activity_want_films.addwantedfilm

class WantFilmsActivity : AppCompatActivity(), View.OnClickListener, WantFilmsAdapter.OnItemClick,
    AddFilmFragment.isendFilm {
    
    lateinit var viewModel: WantFilmsViewModel
    lateinit var wantFilmsAdapter: WantFilmsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_want_films)
        setSupportActionBar(toolBar)
        
        viewModel = ViewModelProvider(
            this,
            WantFilmViewModelFactory(LocalRepository)
        ).get(WantFilmsViewModel::class.java)
        viewModel.filmsLiveData.observe(this, mutableListFilm)
        viewModel.filmLiveData.observe(this,mutableFilm)
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
                openAddFilmFragment()
            }
            R.id.sorting -> {
                wantFilmsAdapter.films.clear()
                wantFilmsAdapter.notifyDataSetChanged()
                viewModel.getAllFilm()
            }
        }
    }
    
    override fun sendFilm(name: String, year: String) {
        val film = Film(0, name, year, "0", "0", "0", "0", false)
        viewModel.insertFilm(film)
        film.id = wantFilmsAdapter.films.size +1
        wantFilmsAdapter.films.add(film)
        wantFilmsAdapter.notifyDataSetChanged()
    }
    
    private val mutableListFilm = Observer<MutableList<Film>> {
        wantFilmsAdapter.films.addAll(it)
        wantFilmsAdapter.notifyDataSetChanged()
    }
    private val mutableFilm = Observer<Film> {
        
    }
    
    override fun onItemClicked(position: Int) {
    
    }
    
    override fun onFavoriteClicked(id: Int) {
    
    }
    
    fun openViewedFilmsActivity() {
        val intent = Intent(this, ViewedFilmsActivity::class.java)
        startActivity(intent)
    }
    
    fun openAddFilmFragment() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.want_film_Fragment_container,
                AddFilmFragment()
            )
            .commit()
    }
}

