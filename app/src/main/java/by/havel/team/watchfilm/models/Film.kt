package by.havel.team.watchfilm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "year") var year: String?,
    @ColumnInfo(name = "star") val star: String?,
    @ColumnInfo(name = "date_added") val date_added: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "category") val category: String?,
    @ColumnInfo(name = "favorites") var favorites: Boolean
)