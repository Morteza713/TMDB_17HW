package com.example.tmdb_17hw.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdb_17hw.data.database.dao.FilmDao
import com.example.tmdb_17hw.data.database.model.Film

@Database(entities = [Film::class], version = 1,exportSchema = true)
abstract class TmdbDatabase:RoomDatabase() {
    abstract fun filmDao() : FilmDao
}