package com.example.marvelac.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CharacterDb::class, CharacterSerieDb::class, CharacterComicDb::class), version = 2, exportSchema = false)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao() : MarvelDao

}