package com.example.marvelac.model.database

import androidx.room.*
import com.example.marvelac.model.*

@Dao
interface MarvelDao {

    @Query("SELECT COUNT(id) FROM character")
    fun getCountCharacter() : Int

    @Query("SELECT * FROM character")
    fun getCharacters() : List<CharacterDb>

    @Transaction
    @Query("SELECT * FROM character WHERE id = :id")
    fun getSeries(id: Int) : List<CharacterWithSeries>

    @Transaction
    @Query("SELECT * FROM character WHERE id = :id")
    fun getComics(id: Int) : List<CharacterWithComics>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters : List<CharacterDb>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeries(series: List<CharacterSerieDb>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComics(comics: List<CharacterComicDb>)

}