package by.havel.team.watchfilm.wantFilmsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.repository.LocalRepository
import kotlinx.coroutines.*

class WantFilmsViewModel(private val repository: LocalRepository) : ViewModel() {
    val getAllFilmsLiveData = MutableLiveData<MutableList<Film>>()
    val getFilmLiveData = MutableLiveData<Film>()
    private val changeDataString = MutableLiveData<String>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    
    fun insertFilm(model: Film) {
        coroutineScope.launch {
            repository.insertFilm(model)
        }
    }
    
    fun getAllFilm() {
        coroutineScope.launch {
            val allFilm = repository.getAllFilm()
            getAllFilmsLiveData.postValue(allFilm)
        }
    }
    
    fun getFilm(id:Int){
        coroutineScope.async {
            val film = repository.getFilm(id)
            getFilmLiveData.postValue(film)
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
    
    fun editFilm(model: Film){
        coroutineScope.launch {
            repository.editFilm(model)
        }
    }
    
    
    fun  saveData(str: String){
        changeDataString.value = str
    }
    
    fun loadData():LiveData<String>{
        return changeDataString
    }

}