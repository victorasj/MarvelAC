package com.example.marvelac.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CharacterDb::class, CharacterSerieDb::class, CharacterComicDb::class), version = 2, exportSchema = false)
abstract class MarvelDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "marvel-db")
            .build()
    }


    abstract fun marvelDao() : MarvelDao

}