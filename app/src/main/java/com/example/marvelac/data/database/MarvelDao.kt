package com.example.marvelac.data.database

import androidx.room.*

@Dao
interface MarvelDao {

    @Query("SELECT COUNT(id) FROM character")
    fun getCountCharacter() : Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters : List<CharacterDb>)

    @Query("SELECT COUNT(id) FROM series WHERE characterId = :characterId")
    fun getCountCharacterSeries(characterId : Long) : Int

    @Query("SELECT COUNT(id) FROM comics WHERE characterId = :characterId")
    fun getCountCharacterComics(characterId : Long) : Int

    @Query("SELECT * FROM character")
    fun getCharacters() : List<CharacterDb>

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacter(characterId : Long) : CharacterDb

    @Query("SELECT COUNT(id) FROM series WHERE characterId = :characterId")
    fun getCountSeries(characterId : Long) : Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeries(series: List<CharacterSerieDb>)

    @Query("SELECT * FROM series WHERE characterId = :characterId")
    fun getSeries(characterId : Long) : List<CharacterSerieDb>

    @Query("SELECT COUNT(id) FROM comics WHERE characterId = :characterId")
    fun getCountComics(characterId : Long) : Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComics(comics: List<CharacterComicDb>)

    @Query("SELECT * FROM comics WHERE characterId = :characterId")
    fun getComics(characterId: Long) : List<CharacterComicDb>




}