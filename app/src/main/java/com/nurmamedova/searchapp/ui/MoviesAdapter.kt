package com.nurmamedova.searchapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nurmamedova.searchapp.data.models.MovieModel
import com.nurmamedova.searchapp.databinding.MovieItemBinding

class MoviesAdapter (
    private val openMovieDescription: (movieId: String) -> Unit = { movieId ->
        Log.d("MovieAdapter:", "Open $movieId")
    }
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var moviesList = mutableListOf<MovieModel>()

    fun updateMovies(movies: List<MovieModel>) {
        val diffCallback = object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = moviesList.size
            override fun getNewListSize(): Int = movies.size

            override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
                return moviesList[oldPosition].movieId == movies[newPosition].movieId
            }

            override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
                return (moviesList[oldPosition].movieId == movies[newPosition].movieId
                        && moviesList[oldPosition].posterUrl == movies[newPosition].posterUrl)
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        moviesList = movies.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    abstract inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(
            movie: MovieModel,
            openMovieDescription: (movieId: String) -> Unit
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position], openMovieDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(
            binding,
            openMovieDescription,
        )
    }

    inner class MoviesViewHolder(
        private val binding: MovieItemBinding,
        private val open: (movieId: String) -> Unit
    ) : MovieViewHolder(binding.root) {

        override fun bind(
            movie: MovieModel,
            openMovieDescription: (movieId: String) -> Unit,
        ) {
            with(binding) {
                if (movie.name == null) tvMovieTitle.text = movie.name
                else tvMovieTitle.text = movie.name


                tvMovieDescription.text =
                    "(${movie.year})"

                Glide
                    .with(imgMovie.context)
                    .load(movie.posterUrlPreview)
                    .centerCrop()
                    .into(imgMovie)

                root.setOnClickListener {
                    open(movie.movieId.toString())
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}