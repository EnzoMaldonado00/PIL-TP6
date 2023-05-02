package com.example.tp6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tp6.R
import com.example.tp6.databinding.ItemRecyclerBinding
import com.example.tp6.service.model.Movie

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_item_recycler, parent, false))
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
            binding.originalTitle.text = itemView.context.getString(R.string.card_title, movie.title)
            binding.overview.text = itemView.context.getString(R.string.card_overview, movie.overview)
            binding.releaseDate.text = itemView.context.getString(R.string.card_release_date, movie.releaseDate)

            Glide.with(itemView.context)
                .load(R.string.card_poster.toString() + movie.poster)
                .into(binding.poster)
        }
    }
}
