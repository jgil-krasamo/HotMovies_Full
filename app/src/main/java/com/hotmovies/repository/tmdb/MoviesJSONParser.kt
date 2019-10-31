package com.hotmovies.repository.tmdb

import com.hotmovies.repository.datamodels.Movie
import org.json.JSONObject

object MoviesJSONParser {

    fun parseJson(response:String?): ArrayList<Movie> {

        val moviesArrayList = ArrayList<Movie>()

        val responseJSONObject = JSONObject(response)
        val moviesJSONArray = responseJSONObject.getJSONArray(MoviesTmdbContract.JSON_ROOT_COLLECTION)

        for (i in 0 until moviesJSONArray.length()) {

            val movieJSON = moviesJSONArray.getJSONObject(i)
            val posterPath = movieJSON.getString(MoviesTmdbContract.JSON_MOVIE_POSTER)
            val movieTitle = movieJSON.getString(MoviesTmdbContract.JSON_MOVIE_TITLE)
            val releaseDate = movieJSON.getString(MoviesTmdbContract.JSON_MOVIE_RELEASE_DATE)
            val voteAverage = movieJSON.getString(MoviesTmdbContract.JSON_MOVIE_VOTE_AVERAGE)
            val overview = movieJSON.getString(MoviesTmdbContract.JSON_MOVIE_OVERVIEW)

            val movieWithPath = Movie(
                    title = movieTitle,
                    posterPath = MoviesTmdbContract.POSTER_BASE_URL + posterPath,
                    releaseDate = releaseDate,
                    voteAverage = voteAverage,
                    overview = overview)

            moviesArrayList.add(movieWithPath)
        }
        return moviesArrayList
    }
}