package by.havel.team.watchfilm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.havel.team.watchfilm.models.Film
import by.havel.team.watchfilm.models.FilmDao

@Database(version = 1,entities = arrayOf(Film::class))
abstract class FilmDatabase : RoomDatabase() {
    abstract fun getFilmDao(): FilmDao
}