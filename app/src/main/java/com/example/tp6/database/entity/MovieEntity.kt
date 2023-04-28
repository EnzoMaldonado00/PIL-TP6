package com.example.tp6.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class MovieEntity(
    @PrimaryKey var id: Int,
    var originalTitle: String,
    var poster: String
)
