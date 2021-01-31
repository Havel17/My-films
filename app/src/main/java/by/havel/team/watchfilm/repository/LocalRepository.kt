package by.havel.team.watchfilm.repository

import by.havel.team.watchfilm.database.Database
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.models.FilmDao

object LocalRepository : Repository {

   var filmDao: FilmDao? = Database.database.getFilmDao()
    override fun insertFilm(model: Film) {
        filmDao?.insertFilm(model)
    }
    
    override fun getFilm(id: Int): Film? {
        return filmDao?.getFilm(id)
    }
    
    override fun getAllFilm(): MutableList<Film>{
        return filmDao?.getAllFilm()!!
    }
    
    override fun removeFilm(id: Int?) {
        filmDao?.deleteFilm(id)
    }
    
    override fun removeAllFilm() {
        filmDao?.removeAll()
    }
    
    override fun editFilm(model: Film) {
        filmDao?.editFilm(model)
    }
    
    
}