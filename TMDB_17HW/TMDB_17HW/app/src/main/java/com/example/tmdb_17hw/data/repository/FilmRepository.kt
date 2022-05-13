package com.example.tmdb_17hw.data.repository

import com.example.tmdb_17hw.data.Result
import com.example.tmdb_17hw.data.database.TmdbDataSource
import com.example.tmdb_17hw.data.database.model.Film
import com.example.tmdb_17hw.data.network.remote.TmdbSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class FilmRepository @Inject constructor(private val tmdbSource: TmdbSource,private val tmdbDataSource: TmdbDataSource) {


        suspend fun getFilmDetails(id: Int) = tmdbSource.getFilmDetails(id)

        suspend fun getSearchFilm(query: String) :Result<MutableList<Film>>{
            val response : MutableList<Film>
            return try {
                response = tmdbSource.getSearchFilm(query).body()!!.results
                coroutineScope {
                    launch {
                        tmdbDataSource.updateDatabase(response)
                    }
                }
                Result.success(response)
            }catch (e: IOException){
                Result.Error(e)
            }
        }
       suspend fun getPopularFilm() : Result <MutableList<Film>> {
            val response : MutableList<Film>
            return try {
                response = tmdbSource.getPopularFilm().body()!!.results
                coroutineScope {
                    launch {
                        tmdbDataSource.updateDatabase(response)
                    }
                }
                Result.success(response)
            }catch (e: IOException){
                Result.Error(e)
            }
        }

        suspend fun getComingSoonFilm() :Result<MutableList<Film>>{
            val response : MutableList<Film>
            return try {
                response = tmdbSource.getUpcomingFilm().body()!!.results
                coroutineScope {
                    launch {
                        tmdbDataSource.updateDatabase(response)
                    }
                }
                Result.success(response)
            }catch (e: IOException){
                Result.Error(e)
            }
        }

}