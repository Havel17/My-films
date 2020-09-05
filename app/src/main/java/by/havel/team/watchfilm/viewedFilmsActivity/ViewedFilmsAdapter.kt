package by.havel.team.watchfilm.viewedFilmsActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.havel.team.watchfilm.R
import by.havel.team.watchfilm.models.Film

class ViewedFilmsAdapter : RecyclerView.Adapter<ViewedFilmsAdapter.ViewdFilmsViewHolder>() {
    var films2 = mutableListOf<Film>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewdFilmsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.model_film, parent, false)
        return ViewdFilmsViewHolder(view)
    }

    override fun getItemCount(): Int = films2.size

    override fun onBindViewHolder(holder: ViewdFilmsViewHolder, position: Int) {
    val film = films2[position]
        holder.bind()


    }

    class ViewdFilmsViewHolder(film2view: View) : RecyclerView.ViewHolder(film2view) {


        fun bind() {

        }
    }
}