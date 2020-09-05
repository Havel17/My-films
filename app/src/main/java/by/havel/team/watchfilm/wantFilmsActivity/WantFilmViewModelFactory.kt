package by.havel.team.watchfilm.wantFilmsActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.havel.team.watchfilm.repository.LocalRepository

class WantFilmViewModelFactory(private val repository: LocalRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == WantFilmsViewModel::class.java)
            WantFilmsViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }


}