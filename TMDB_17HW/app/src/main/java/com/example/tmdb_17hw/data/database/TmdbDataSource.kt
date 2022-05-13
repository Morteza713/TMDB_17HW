package com.example.tmdb_17hw.data.database

import com.example.tmdb_17hw.data.database.dao.FilmDao
import com.example.tmdb_17hw.data.database.model.Film
import javax.inject.Inject

class TmdbDataSource @Inject constructor(private val filmDao: FilmDao) {
    suspend fun updateDatabase(list: MutableList<Film>) {
        filmDao.updateDatabase(list)
    }
    fun updateFilmDatabase(list: MutableList<Film>) {
        filmDao.updateFilm(list)
    }
}