package com.hotmovies.screens.movielistmain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hotmovies.R
import com.hotmovies.repository.datamodels.Movie
import com.hotmovies.screens.extensions.loadUrl

class MovieListRecyclerViewAdapter (

        private var movieList: List<Movie>,

        private val movieListRecyclerViewOnItemClickListener: AdapterView.OnItemClickListener

   ) : RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_imageview, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val movie = movieList[position]

        // Populate user interface
        viewHolder.movieImageView.loadUrl(movie.posterPath)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    /** ViewHolder pattern: Inner class needed to keep the references between widgets and data to improve the performance */
    inner class ViewHolder (

            itemView: View

    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var movieImageView: ImageView = itemView.findViewById(R.id.movieItemImageView)

        override fun onClick(view: View?) {

            movieListRecyclerViewOnItemClickListener.onItemClick(null, view, adapterPosition, view?.id!!.toLong())
        }
    }
}
