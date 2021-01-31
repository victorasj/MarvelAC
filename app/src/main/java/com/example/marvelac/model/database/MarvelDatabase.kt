package com.example.marvelac.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelac.model.CharacterComicDb
import com.example.marvelac.model.CharacterDb
import com.example.marvelac.model.CharacterSerieDb

@Database(entities = arrayOf(CharacterDb::class, CharacterSerieDb::class, CharacterComicDb::class), version = 1, exportSchema = false)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao() : MarvelDao

}