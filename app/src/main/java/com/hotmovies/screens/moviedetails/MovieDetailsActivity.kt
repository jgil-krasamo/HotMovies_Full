package com.hotmovies.screens.moviedetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hotmovies.R
import com.hotmovies.screens.extensions.loadUrl

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details_activity)

        val posterImageView = findViewById<ImageView>(R.id.posterImageView)
        val titleTextView = findViewById<TextView>(R.id.movieTitleTextView)
        val releaseTextView = findViewById<TextView>(R.id.releaseTextView)
        val ratingTextView = findViewById<TextView>(R.id.ratingTextView)
        val overviewTextView = findViewById<TextView>(R.id.overviewTextView)

        val titleString = intent.getStringExtra("movie_title")
        val posterPathString = intent.getStringExtra("movie_posterPath")
        val releaseDateString = intent.getStringExtra("movie_releaseDate")
        val voteAverageString = intent.getStringExtra("movie_voteAverage")
        val overviewString = intent.getStringExtra("movie_overview")
        val backdropPathString = intent.getStringExtra("movie_backdropPath")

        posterImageView.loadUrl(posterPathString)

        titleTextView.text = titleString
        releaseTextView.text = releaseDateString
        ratingTextView.text = voteAverageString
        overviewTextView.text = overviewString
    }
}
