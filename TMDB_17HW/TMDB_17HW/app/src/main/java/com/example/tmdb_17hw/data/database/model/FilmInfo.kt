package com.example.tmdb_17hw.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmInfo(
    @PrimaryKey()
    val id: Int,
    val overview: String,
    val poster_path: String
)