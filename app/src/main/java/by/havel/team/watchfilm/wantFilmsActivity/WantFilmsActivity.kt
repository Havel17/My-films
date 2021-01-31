package by.havel.team.watchfilm.wantFilmsActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.havel.team.watchfilm.MainMenuFragment
import by.havel.team.watchfilm.R
import by.havel.team.watchfilm.database.Database
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.repository.LocalRepository
import by.havel.team.watchfilm.viewedFilmsActivity.ViewedFilmsActivity
import by.havel.team.watchfilm.wantFilmsActivity.fragments.AddFilmFragment
import by.havel.team.watchfilm.wantFilmsActivity.fragments.FilmMenuFragment
import kotlinx.android.synthetic.main.activity_want_films.*
import kotlinx.android.synthetic.main.activity_want_films.addwantedfilm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WantFilmsActivity : AppCompatActivity(), View.OnClickListener, WantFilmsAdapter.OnItemClick,
    AddFilmFragment.isendFilm, FilmMenuFragment.imenuFilm, MainMenuFragment.imainmenuFilm {
    
    lateinit var viewModel: WantFilmsViewModel
    lateinit var wantFilmsAdapter: WantFilmsAdapter
    
    private lateinit var filmList: MutableList<Film>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_want_films)
        setSupportActionBar(toolBar)
        Database.getDatabase(this)
        
        viewModel = ViewModelProvider(
            this,
            WantFilmViewModelFactory(LocalRepository)
        ).get(WantFilmsViewModel::class.java)
        viewModel.getAllFilmsLiveData.observe(this, getAllFilms)
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
                openMainMenuFragment()
            }
        }
    }
    
    private val getAllFilms = Observer<MutableList<Film>> {
        filmList = it
        wantFilmsAdapter.films.clear()
        wantFilmsAdapter.films.addAll(it)
        wantFilmsAdapter.notifyDataSetChanged()
    }
    
    override fun sendFilm(name: String, year: String) {
        val film = Film(name, year, "0", "0", "0", "0", false)
        viewModel.insertFilm(film)
        viewModel.getAllFilm()
    }
    
    override fun removefilm(position: Int) {
        val filmid = wantFilmsAdapter.films.get(position).id
        viewModel.removeFilm(filmid)
        wantFilmsAdapter.films.removeAt(position)
        wantFilmsAdapter.notifyDataSetChanged()
    }
    
    override fun changeFilm(position: Int) {
       // openAddFilmFragment()
        // Изменение фильма
    }
    
    override fun update() {
        viewModel.getAllFilm()
    }
    // поиск части слова в названии фильма
    override fun findfilm(findWord: String) {
        val findedFilms : MutableList<Film> = mutableListOf() //создаем новый список с найдеными фильмами
        if (findWord== ""){
            viewModel.getAllFilm()
        }else {
            filmList.forEachIndexed { filmId, film ->
                var count = 0  // количество найденных букв идущих подрят
                var j = 0  // индекс в слове, которое ищем ( словеныш)
                for (i in 0..film.name!!.lastIndex) {  // перебираем все буквы в названии
                    if (film.name!![i].equals(
                            findWord[j],
                            ignoreCase = true
                        )
                    ) { // сравниваем букву названия и первую букву словеныша
                        count++  // если нашли, увеличиваем кол-во букв идущих подрят и в сл цикле сравниваем сл букву названия и вторую букву словеныша
                        j++
                        if (count == findWord.length) {  // если кол-во идущих букв подрят равен длине слова, которое мы ищем, значит мы нашли то что надо
                            j = 0
                            count = 0
                            findedFilms.add(film) // добавляем в новый список найденых фильмов
                            return@forEachIndexed
                        }
                    } else {
                        j = 0
                        count = 0
                    }
                }
        
            }// ну а тут мы типа обновляем экран найдеными словами, сначала чистим, потом добавляем список с новыми фильмами, после говорим адаптеру шо шото изменилось
            wantFilmsAdapter.films.clear()
            wantFilmsAdapter.films.addAll(findedFilms)
            wantFilmsAdapter.notifyDataSetChanged()
        }
    }
    
    override fun onItemClicked(position: Int) {
        openFilmMenuFragment(position);
    }
    
    override fun onFavoriteClicked(film: Film) {
        viewModel.editFilm(film)
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
    
    fun openFilmMenuFragment(position: Int) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.want_film_Fragment_container,
                FilmMenuFragment(position)
            )
            .commit()
    }
    
    fun openMainMenuFragment() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.want_film_Fragment_container,
                MainMenuFragment()
            )
            .commit()
    }

}


