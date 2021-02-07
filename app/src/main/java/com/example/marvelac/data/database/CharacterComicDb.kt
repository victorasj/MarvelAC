package com.example.marvelac.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
data class CharacterComicDb (
    @PrimaryKey var id : Long,
    var name: String,
    var imageURL: String,
    var characterId: Long
)
