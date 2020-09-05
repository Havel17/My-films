package by.havel.team.watchfilm.wantFilmsActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.havel.team.watchfilm.R
import by.havel.team.watchfilm.models.Film
import kotlinx.android.synthetic.main.model_film.view.*

class WantFilmsAdapter(private val callback: OnItemClick) :
    RecyclerView.Adapter<WantFilmsAdapter.WantFilmsViewHolder>() {
    
    var films = mutableListOf<Film>()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WantFilmsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.model_film, parent, false)
        return WantFilmsViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: WantFilmsViewHolder, position: Int) {
        
        val film = films[position]
        holder.bind(film)
        
        holder.itemView.setOnClickListener {
            callback.onItemClicked(holder.adapterPosition)
        }
    }
    
    override fun getItemCount(): Int = films.size
    
    class WantFilmsViewHolder(filmView: View) : RecyclerView.ViewHolder(filmView) {
        private val favorite = filmView.favorite
        private val filmid = filmView.filmid
        private val name = filmView.name
        private val year = filmView.year
//        private val adddate = filmView.adddate
//        private val star = filmView.star
        
        fun bind(film: Film) {
            
            favorite.isChecked = film.favorites
            filmid.text = film.id.toString()
            name.text = film.name
            year.text = film.year
//            adddate.text = film.date_added.toString()
//            star.text = film.star.toString()
        }
    }
    
    interface OnItemClick {
        fun onItemClicked(position: Int)
    }
}