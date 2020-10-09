package by.havel.team.watchfilm.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDao {
    @Insert
    fun insertFilm(model: Film?)
    
    @Query("SELECT * FROM film WHERE id LIKE :id ")
    fun getFilm(id: Int): Film
    
    @Query("SELECT * FROM film")
    fun getAllFilm(): MutableList<Film>
    
    @Query("DELETE FROM film WHERE id LIKE :id")
    fun deleteFilm(id: Int?)
    
    @Query("DELETE FROM film")
    fun removeAll()
    
}