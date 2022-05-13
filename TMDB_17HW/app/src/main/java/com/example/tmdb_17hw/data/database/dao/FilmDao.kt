package com.example.tmdb_17hw.data.database.dao

import androidx.room.*
import com.example.tmdb_17hw.data.database.model.Film

@Dao
interface FilmDao {
    @Insert
    fun insertFilm(list: MutableList<Film>)
    @Query("DELETE FROM Film")
    fun removeAll()
    @Update
    fun updateFilm(list: MutableList<Film>)
    @Transaction
    suspend fun updateDatabase(list: MutableList<Film>) {
        removeAll()
        insertFilm(list)
    }
}