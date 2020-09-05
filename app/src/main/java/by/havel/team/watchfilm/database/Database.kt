package by.havel.team.watchfilm.database

import android.app.Application
import android.content.Context
import androidx.room.Room

object Database{
    lateinit var database: FilmDatabase

    fun getDatabase(context: Context):FilmDatabase?{
        if (!::database.isInitialized){
            database = Room.databaseBuilder(
                context,
                FilmDatabase::class.java,
                "Film Database"
            ).build()
        }
        return database
    }
}