package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Character
import com.example.domain.CharacterComic
import com.example.domain.CharacterSerie

class CharactersRepository (
        private val localDataSource: LocalDataSource,
        private val remoteDataSource: RemoteDataSource
) {

    suspend fun getCharacters(url : String) :  List<Character> {
        if(localDataSource.isEmptyCharacter()){
            val characters = remoteDataSource.getCharacters(url)
            localDataSource.saveCharacters(characters)
        }
        return localDataSource.getCharacters()
    }

    suspend fun getCharacter(characterId : Long) : Character {
        return localDataSource.getCharacter(characterId)
    }

    suspend fun getCharacterSeries(characterId : Long, url : String) :  List<CharacterSerie> {
        if(localDataSource.isEmptyCharacterSeries(characterId)){
            val characterSeries = remoteDataSource.getCharacterSeries(characterId, url)
            localDataSource.saveCharacterSeries(characterSeries)
        }
        return localDataSource.getCharacterSeries(characterId)
    }

    suspend fun getCharacterComics(characterId : Long, url : String) :  List<CharacterComic> {
        if(localDataSource.isEmptyCharacterComics(characterId)){
            val characterComics = remoteDataSource.getCharacterComics(characterId, url)
            localDataSource.saveCharacterComics(characterComics)
        }
        return localDataSource.getCharacterComics(characterId)
    }
}