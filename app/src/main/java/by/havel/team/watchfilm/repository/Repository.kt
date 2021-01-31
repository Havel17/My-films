package by.havel.team.watchfilm.repository

import by.havel.team.watchfilm.models.Film

interface Repository {
    fun insertFilm(model: Film)
    fun getFilm(id:Int):Film?
    fun getAllFilm():MutableList<Film>
    fun removeFilm(Position: Int?)
    fun removeAllFilm()
    fun editFilm(model: Film)
}