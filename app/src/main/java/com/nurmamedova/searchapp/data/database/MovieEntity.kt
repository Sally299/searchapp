package com.nurmamedova.searchapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val name: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String,
    val description: String?
)