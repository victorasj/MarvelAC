package com.example.marvelac.model

interface LocalDataSource {

    fun getCharacterCount() : Int
    fun getCharacters() : List<CharacterDb>
    fun getSeriesCharacter(idCharacter : Int) : List<CharacterSerieDb>
    fun getComicsCharacter(idCharacter : Int) : List<CharacterComicDb>
}