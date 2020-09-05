package by.havel.team.watchfilm.database

import android.app.Application
import by.havel.team.watchfilm.repository.LocalRepository

class DataBaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Database.getDatabase(this)
    }
}