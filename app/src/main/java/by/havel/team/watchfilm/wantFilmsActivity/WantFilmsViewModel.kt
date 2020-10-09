package by.havel.team.watchfilm.wantFilmsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.repository.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WantFilmsViewModel(private val repository: LocalRepository) : ViewModel() {
    val filmsLiveData = MutableLiveData<MutableList<Film>>()
    val filmLiveData = MutableLiveData<Film>()
    
    private val changeDataString = MutableLiveData<String>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    
    fun insertFilm(model: Film) {
        coroutineScope.launch {
            repository.insertFilm(model)
        }
    }
    
    fun getFilm(id: Int) {
        coroutineScope.launch {
            val film = repository.getFilm(id)
            filmLiveData.postValue(film)
        }
    }
    
    fun getAllFilm() {
        coroutineScope.launch {
            val allFilm = repository.getAllFilm()
            filmsLiveData.postValue(allFilm)
        }
    }
    
    fun removeFilm(id: Int?) {
        coroutineScope.launch {
            repository.removeFilm(id)
        }
    }
    
    fun removeAllFilm() {
        coroutineScope.launch {
            repository.removeAllFilm()
        }
    }
    
    
    fun  saveData(str: String){
        changeDataString.value = str
    }
    
    fun loadData():LiveData<String>{
        return changeDataString
    }
    
}