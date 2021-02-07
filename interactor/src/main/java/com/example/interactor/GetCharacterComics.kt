package com.example.interactor

import com.example.data.repository.CharactersRepository
import com.example.domain.Character
import com.example.domain.CharacterComic
import com.example.domain.CharacterSerie

class GetCharacterComics(private val charactersRepository: CharactersRepository) {
    suspend fun invoke(characterId : Long, url : String) : List<CharacterComic> = charactersRepository.getCharacterComics(characterId, url)
}