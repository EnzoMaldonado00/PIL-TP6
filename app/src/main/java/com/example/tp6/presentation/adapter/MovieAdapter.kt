package com.example.tp6.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp6.R
import com.example.tp6.databinding.ItemRecyclerBinding
import com.example.tp6.domain.entity.Movie

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRecyclerBinding.bind(itemView)

        fun bind(movie: Movie) {
            binding.id.text = itemView.context.getString(R.string.card_id, movie.id.toString())
            binding.title.text = itemView.context.getString(R.string.card_title, movie.title)
            binding.overview.text = itemView.context.getString(R.string.card_overview, movie.overview)
            binding.releaseDate.text = itemView.context.getString(R.string.card_release_date, movie.releaseDate)
            val poster = itemView.context.getString(R.string.card_poster, movie.poster)

            Glide.with(itemView.context)
                .load(poster)
                .into(binding.poster)
        }
    }
}
