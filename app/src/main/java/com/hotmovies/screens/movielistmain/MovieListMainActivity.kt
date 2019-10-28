package com.hotmovies.screens.movielistmain

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hotmovies.R
import com.hotmovies.repository.datamodels.Movie
import com.hotmovies.repository.tmdb.MoviesJSONParser
import com.hotmovies.repository.tmdb.MoviesTmdbManager
import com.hotmovies.screens.moviedetails.MovieDetailsActivity
import kotlinx.android.synthetic.main.movie_list_main_activity.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
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

        // Retrieve the movies information
        val data = retrieveMoviesAsync()

        runBlocking {

            movieList = MoviesJSONParser.parseJson(data.await())

            // Populate the recycler view with the movie list
            val movieListRecyclerViewAdapter = MovieListRecyclerViewAdapter(movieList as ArrayList<Movie>, MovieListRecyclerViewOnItemClickListener())
            movieListRecyclerView.adapter = movieListRecyclerViewAdapter

            // If the contactList is empty, show the emptyTextView. Otherwise, show the contactListRecyclerView
            if (movieList == null || (movieList as ArrayList<Movie>).size == 0) {
                movieListRecyclerView.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE

            } else {
                movieListRecyclerView.visibility = View.VISIBLE
                emptyTextView.visibility = View.GONE
            }
        }
    }

    private fun retrieveMoviesAsync() = GlobalScope.async {
        MoviesTmdbManager.retrieveMovies()
    }

    /** Called when the user clicks on an item of the movie list */
    inner class MovieListRecyclerViewOnItemClickListener : View.OnClickListener {

        override fun onClick(view: View?) {

            val itemIndex = movieListRecyclerView!!.indexOfChild(view)

            val movie = movieList!![itemIndex]

            val intent = Intent()
            intent.setClass(this@MovieListMainActivity, MovieDetailsActivity::class.java)
            intent.putExtra("movie_title", movie.title)
            intent.putExtra("movie_posterPath", movie.posterPath)
            intent.putExtra("movie_releaseDate", movie.releaseDate)
            intent.putExtra("movie_voteAverage", movie.voteAverage)
            intent.putExtra("movie_overview", movie.overview)
            intent.putExtra("movie_backdropPath", movie.backdropPath)

            startActivity(intent)

        }
    }
}