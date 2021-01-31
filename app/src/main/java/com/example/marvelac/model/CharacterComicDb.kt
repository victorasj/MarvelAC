package com.example.marvelac.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "comics")
data class CharacterComicDb (
    @PrimaryKey var id : Long,
    var name: String,
    var description: String,
    var imageURL: String,
    var characterId: Int
)

data class CharacterWithComics (
    @Embedded val characterDb: CharacterDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val comics: List<CharacterComicDb>
)
