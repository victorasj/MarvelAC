package com.example.marvelac.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "series")
data class CharacterSerieDb (
    @PrimaryKey var id : Long,
    var name: String,
    var imageURL: String,
    var characterId: Long
)

data class CharacterWithSeries (
    @Embedded val characterDb: CharacterDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "characterId"
    )
    val series: List<CharacterSerieDb>
)
