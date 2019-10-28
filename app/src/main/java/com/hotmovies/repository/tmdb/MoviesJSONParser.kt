package com.hotmovies.repository.tmdb

import com.hotmovies.repository.datamodels.Movie
import org.json.JSONObject

object MoviesJSONParser {

    fun parseJson(response:String?): ArrayList<Movie> {

        val moviesArrayList = ArrayList<Movie>()

        val responseJSONObject = JSONObject(response)
        val moviesJSONArray = responseJSONObject.getJSONArray(MoviesTmdbDbContract.JSON_ROOT_COLLECTION)

        for (i in 0 until moviesJSONArray.length()) {

            val movieJSON = moviesJSONArray.getJSONObject(i)
            val movieId = movieJSON.getString(MoviesTmdbDbContract.JSON_MOVIE_ID)
            val posterPath = movieJSON.getString(MoviesTmdbDbContract.JSON_MOVIE_POSTER)
            val movieTitle = movieJSON.getString(MoviesTmdbDbContract.JSON_MOVIE_TITLE)
            val releaseDate = movieJSON.getString(MoviesTmdbDbContract.JSON_MOVIE_RELEASE_DATE)
            val voteAverage = movieJSON.getString(MoviesTmdbDbContract.JSON_MOVIE_VOTE_AVERAGE)
            val overview = movieJSON.getString(MoviesTmdbDbContract.JSON_MOVIE_OVERVIEW)
            val backdropPath = movieJSON.getString(MoviesTmdbDbContract.JSON_MOVIE_BACKDROP)

            val movieWithPath = Movie(
                    id = movieId,
                    title = movieTitle,
                    posterPath = MoviesTmdbDbContract.POSTER_BASE_URL + posterPath,
                    releaseDate = releaseDate,
                    voteAverage = voteAverage,
                    overview = overview,
                    backdropPath = MoviesTmdbDbContract.BACKDROP_BASE_URL + backdropPath)

            moviesArrayList.add(movieWithPath)
        }
        return moviesArrayList
    }
}