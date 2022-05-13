package com.example.tmdb_17hw.data.network.remote

import com.example.tmdb_17hw.data.network.model.MovieDetail
import com.example.tmdb_17hw.data.network.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFilm {

    @GET("movie/{movie_id}")
    suspend fun getFilmDetails(@Path("movie_id")id:Int) : Response<MovieDetail>

    @GET("movie/popular")
    suspend fun getPopularFilm(@Query("page") page:Int) : Response<MovieList>

    @GET("movie/upcoming")
    suspend fun getUpcomingFilm(@Query("page")page: Int) : Response<MovieList>

    @GET("search/movie")
    suspend fun getSearchFilm(@Query("query") query:String) : Response<MovieList>
}