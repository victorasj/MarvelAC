package com.example.marvelac.data.server

import com.example.data.source.RemoteDataSource
import com.example.domain.Character
import com.example.domain.CharacterComic
import com.example.domain.CharacterSerie
import com.example.marvelac.data.convertToDomainCharacter
import com.example.marvelac.data.convertToDomainCharacterComic
import com.example.marvelac.data.convertToDomainCharacterSerie

class MarvelDataSource : RemoteDataSource {

    override suspend fun getCharacters(url: String, limit: Int, offset: Int): List<Character> =
            RetrofitInstance.SERVICE
                    .getCharacters(url, limit, offset).data.results.map { it.convertToDomainCharacter() }

    override suspend fun getCharacterSeries(characterId : Long, url: String): List<CharacterSerie> =
        RetrofitInstance.SERVICE
            .getCharacterSeries(url).data.results.map { it.convertToDomainCharacterSerie(characterId) }

    override suspend fun getCharacterComics(characterId : Long, url: String): List<CharacterComic> =
        RetrofitInstance.SERVICE
            .getCharacterComics(url).data.results.map { it.convertToDomainCharacterComic(characterId) }
}