package com.example.data.source

import com.example.domain.Character
import com.example.domain.CharacterComic
import com.example.domain.CharacterSerie

interface RemoteDataSource {
    suspend fun getCharacters(url : String, limit: Int = 200, offset: Int = 0) :  List<Character>
    suspend fun getCharacterSeries(characterId : Long, url : String) :  List<CharacterSerie>
    suspend fun getCharacterComics(characterId : Long, url : String) :  List<CharacterComic>
}