package com.example.tmdb_17hw.data.network.model

import com.example.tmdb_17hw.data.database.model.Film

data class MovieList(
    val page: Int,
    val results: MutableList<Film>,
    val total_pages: Int,
    val total_results: Int
)
