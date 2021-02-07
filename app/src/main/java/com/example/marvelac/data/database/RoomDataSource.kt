package com.example.marvelac.data.database

import com.example.data.source.LocalDataSource
import com.example.domain.Character
import com.example.domain.CharacterComic
import com.example.domain.CharacterSerie
import com.example.marvelac.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db : MarvelDatabase) : LocalDataSource {

    private val marvelDao = db.marvelDao()

    override suspend fun isEmptyCharacter(): Boolean = withContext(Dispatchers.IO) { marvelDao.getCountCharacter() <= 0}

    override suspend fun saveCharacters(characters: List<Character>) { withContext(Dispatchers.IO) { marvelDao.insertCharacters( characters.map { it.toRoomCharacter() } )} }

    override suspend fun getCharacters(): List<Character> = withContext(Dispatchers.IO) { marvelDao.getCharacters().map { it.toDomainCharacter() } }

    override suspend fun getCharacter(characterId: Long): Character = withContext(Dispatchers.IO) { marvelDao.getCharacter(characterId).toDomainCharacter() }

    override suspend fun isEmptyCharacterSeries(characterId: Long): Boolean = withContext(Dispatchers.IO) { marvelDao.getCountSeries(characterId) <= 0}

    override suspend fun saveCharacterSeries(characterSeries: List<CharacterSerie>) { withContext(Dispatchers.IO) { marvelDao.insertSeries( characterSeries.map { it.toRoomCharacterSerie() } )} }

    override suspend fun getCharacterSeries(characterId: Long): List<CharacterSerie> = withContext(Dispatchers.IO) { marvelDao.getSeries(characterId).map { it.toDomainCharacterSerie() }}

    override suspend fun isEmptyCharacterComics(characterId: Long): Boolean = withContext(Dispatchers.IO) { marvelDao.getCountComics(characterId) <= 0}

    override suspend fun saveCharacterComics(characterComics: List<CharacterComic>) { withContext(Dispatchers.IO) { marvelDao.insertComics( characterComics.map { it.toRoomCharacterComic() } )} }

    override suspend fun getCharacterComics(characterId: Long): List<CharacterComic> = withContext(Dispatchers.IO) { marvelDao.getComics(characterId).map { it.toDomainCharacterComic() }}

}