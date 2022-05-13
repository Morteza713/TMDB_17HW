package com.example.tmdb_17hw.data.database.dao

import androidx.room.*
import com.example.tmdb_17hw.data.database.model.Film

@Dao
interface FilmDao {
    @Insert
    fun insertMovieList(list: MutableList<Film>)
    @Query("DELETE FROM Film")
    fun deleteAll()
    @Update
    fun updateFilm(list: MutableList<Film>)
    @Transaction
    suspend fun updateDatabase(list: MutableList<Film>) {
        deleteAll()
        insertMovieList(list)
    }
}