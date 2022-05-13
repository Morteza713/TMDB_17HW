package com.example.tmdb_17hw.data.database.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")val id: Int,
    @ColumnInfo(name = "genreName")val name: String
)