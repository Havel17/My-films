package by.havel.team.watchfilm.viewedFilmsActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.havel.team.watchfilm.database.Database.getDatabase
import by.havel.team.watchfilm.database.FilmDatabase
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.repository.Repository

class ViewedFilmsViewModel(repository: Repository): ViewModel() {
    val filmsLiveData = MutableLiveData<List<Film>>()

    fun getFilm() {

    }


}