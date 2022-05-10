package com.android.assignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.assignment.databinding.ListItemAdapterBinding
import com.bumptech.glide.Glide
import com.android.assignment.service.model.Movie

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    private var movieList = mutableListOf<Movie>()

    fun setMovieList(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ListItemAdapterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.name.text = movie.release_date.plus("  ,")
        holder.binding.imageUrl.text = movie.status
        holder.binding.category.text = movie.tagline
        holder.binding.desc.text = movie.title
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: ListItemAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

}
