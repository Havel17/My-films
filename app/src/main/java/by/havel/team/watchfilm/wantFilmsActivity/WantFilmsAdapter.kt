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
        holder.bind(film, position+1)
        
        holder.itemView.setOnClickListener {
            callback.onItemClicked(holder.adapterPosition)
        }
        holder.itemView.favorite.setOnClickListener {
            film.favorites = !film.favorites
            callback.onFavoriteClicked(film)
            holder.isFavorite(film)
            
        }
    }
    
    override fun getItemCount(): Int = films.size
    
    class WantFilmsViewHolder(filmView: View) : RecyclerView.ViewHolder(filmView) {
        private val favorite = filmView.favorite
        private val filmid = filmView.filmid
        private val name = filmView.name
        private val year = filmView.year
        
        fun bind(film: Film, id: Int) {
            isFavorite(film)
            filmid.text = id.toString()
            name.text = film.name
            year.text = film.year
        }
        
        fun isFavorite(film: Film) {
            if (film.favorites) {
                favorite.setImageResource(R.drawable.ic_star)
                return
            }
            favorite.setImageResource(R.drawable.ic_starsss)
        }
    }
    
    interface OnItemClick {
        fun onItemClicked(position: Int)
        fun onFavoriteClicked(film: Film)
    }
    
}

