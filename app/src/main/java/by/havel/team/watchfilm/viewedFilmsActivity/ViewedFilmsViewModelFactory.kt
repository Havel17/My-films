package by.havel.team.watchfilm.viewedFilmsActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.havel.team.watchfilm.repository.Repository

class ViewedFilmsViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == ViewedFilmsViewModel::class.java)
            ViewedFilmsViewModel(repository) as T
        else
            throw IllegalArgumentException("Wrong ViewModel class")
    }

}
