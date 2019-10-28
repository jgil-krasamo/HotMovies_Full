package com.hotmovies.repository.tmdb

object MoviesTmdbDbContract {

    private const val TMDB_API_KEY="02a6d79992ed3e3da1f638dec4c74770"

    const val POPULAR_MOVIES_BASE_URL: String = "http://api.themoviedb.org/3/movie/popular?api_key=${TMDB_API_KEY}"

    const val POSTER_BASE_URL: String = "http://image.tmdb.org/t/p/w185"
    const val BACKDROP_BASE_URL: String = "https://image.tmdb.org/t/p/w300"

    const val JSON_ROOT_COLLECTION: String = "results"

    const val JSON_MOVIE_ID: String = "id"
    const val JSON_MOVIE_TITLE: String = "title"
    const val JSON_MOVIE_POSTER: String = "poster_path"
    const val JSON_MOVIE_RELEASE_DATE: String = "release_date"
    const val JSON_MOVIE_VOTE_AVERAGE: String = "vote_average"
    const val JSON_MOVIE_OVERVIEW: String = "overview"
    const val JSON_MOVIE_BACKDROP: String = "backdrop_path"
}