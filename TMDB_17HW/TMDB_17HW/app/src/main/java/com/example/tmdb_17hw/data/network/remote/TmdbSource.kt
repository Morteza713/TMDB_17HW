package com.example.tmdb_17hw.data.network.remote

import com.example.tmdb_17hw.data.network.model.MovieDetail
import com.example.tmdb_17hw.data.network.model.MovieList
import retrofit2.Response

interface TmdbSource {
    suspend fun getFilmDetails(id:Int) : Response<MovieDetail>
    suspend fun getSearchFilm(query: String) : Response<MovieList>
    suspend fun getPopularFilm() : Response<MovieList>
    suspend fun getUpcomingFilm() : Response<MovieList>
}