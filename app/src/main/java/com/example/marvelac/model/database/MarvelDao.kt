package com.example.marvelac.model.database

import androidx.room.*
import com.example.marvelac.model.*

@Dao
interface MarvelDao {

    @Query("SELECT COUNT(id) FROM character")
    fun getCountCharacter() : Int

    @Query("SELECT COUNT(id) FROM series WHERE characterId = :characterId")
    fun getCountCharacterSeries(characterId : Long) : Int

    @Query("SELECT COUNT(id) FROM comics WHERE characterId = :characterId")
    fun getCountCharacterComics(characterId : Long) : Int

    @Query("SELECT * FROM character")
    fun getCharacters() : List<CharacterDb>


    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacter(characterId : Long) : CharacterDb

    @Query("SELECT * FROM series WHERE characterId = :characterId")
    fun getSeries(characterId : Long) : List<CharacterSerieDb>

    @Query("SELECT * FROM comics WHERE characterId = :characterId")
    fun getComics(characterId: Long) : List<CharacterComicDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters : List<CharacterDb>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeries(series: List<CharacterSerieDb>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComics(comics: List<CharacterComicDb>)

}