package com.example.data.source

import com.example.domain.Character
import com.example.domain.CharacterComic
import com.example.domain.CharacterSerie

interface LocalDataSource {
    suspend fun isEmptyCharacter() : Boolean
    suspend fun saveCharacters(characters : List<Character>)
    suspend fun getCharacters() :  List<Character>
    suspend fun getCharacter(characterId : Long) :  Character
    suspend fun isEmptyCharacterSeries(characterId: Long) : Boolean
    suspend fun saveCharacterSeries(characterSeries : List<CharacterSerie>)
    suspend fun getCharacterSeries(characterId: Long) :  List<CharacterSerie>
    suspend fun isEmptyCharacterComics(characterId: Long) : Boolean
    suspend fun saveCharacterComics(characterComics : List<CharacterComic>)
    suspend fun getCharacterComics(characterId: Long) :  List<CharacterComic>
}