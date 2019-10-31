package com.hotmovies.screens.movielistmain

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hotmovies.R
import com.hotmovies.repository.datamodels.Movie
import com.hotmovies.repository.tmdb.MoviesJSONParser
import com.hotmovies.repository.tmdb.MoviesTmdbManager
import com.hotmovies.screens.moviedetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.movie_list_main_activity.*
import kotlinx.coroutines.runBlocking

class MovieListMainActivity : AppCompatActivity() {

    /* Content objects */
    private var movieList: List<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_main_activity)

        val movieList = findViewById<RecyclerView>(R.id.movieListRecyclerView)
        movieList!!.layoutManager = GridLayoutManager(this, 3)
    }

    override fun onStart() {
        super.onStart()

        retrievePopularMovies()
    }

    private fun retrievePopularMovies() {

        // Retrieve the information of popular movies
        val data = MoviesTmdbManager.retrievePopularMoviesAsync()

        runBlocking {

            movieList = MoviesJSONParser.parseJson(data.await())

            // Populate the recycler view with the movie list
            val movieListRecyclerViewAdapter = MovieListRecyclerViewAdapter(movieList as ArrayList<Movie>, MovieListRecyclerViewOnItemClickListener())
            movieListRecyclerView.adapter = movieListRecyclerViewAdapter

            // If the contactList is empty, show the emptyTextView. Otherwise, show the contactListRecyclerView
            if (movieList == null || movieList!!.isEmpty()) {
                movieListRecyclerView.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE

            } else {
                movieListRecyclerView.visibility = View.VISIBLE
                emptyTextView.visibility = View.GONE
            }
        }
    }

    /** Called when the user clicks on an item of the movie list */
    inner class MovieListRecyclerViewOnItemClickListener : AdapterView.OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            val movie = movieList!![position]

            val intent = Intent()
            intent.setClass(this@MovieListMainActivity, MovieDetailsActivity::class.java)
            intent.putExtra("movie_title", movie.title)
            intent.putExtra("movie_posterPath", movie.posterPath)
            intent.putExtra("movie_releaseDate", movie.releaseDate)
            intent.putExtra("movie_voteAverage", movie.voteAverage)
            intent.putExtra("movie_overview", movie.overview)

            startActivity(intent)
        }
    }
}