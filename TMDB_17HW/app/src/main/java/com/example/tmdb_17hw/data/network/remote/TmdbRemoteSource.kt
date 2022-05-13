package com.example.tmdb_17hw.data.network.remote

import com.example.tmdb_17hw.data.network.model.MovieDetail
import com.example.tmdb_17hw.data.network.model.MovieList
import retrofit2.Response
import javax.inject.Inject

class TmdbRemoteSource @Inject constructor(private val apiFilm: ApiFilm) : TmdbSource {
    override suspend fun getFilmDetails(id: Int): Response<MovieDetail> {
        return apiFilm.getFilmDetails(id)
    }

    override suspend fun getSearchFilm(query: String): Response<MovieList> {
        return apiFilm.getSearchFilm(query)
    }

    override suspend fun getPopularFilm(): Response<MovieList> {
       return apiFilm.getPopularFilm(1)
    }

    override suspend fun getUpcomingFilm(): Response<MovieList> {
        return apiFilm.getUpcomingFilm(10)
    }
}