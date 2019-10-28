package com.hotmovies.repository.datamodels

data class Movie (

    val id: String = "",
    var title: String = "",
    var posterPath: String = "",
    var releaseDate: String = "",
    var voteAverage: String = "",
    var overview: String = "",
    var backdropPath: String = ""
)