package com.example.interactor

import com.example.data.repository.CharactersRepository
import com.example.domain.Character

class GetCharacters(private val charactersRepository: CharactersRepository) {
    suspend fun invoke(url : String) : List<Character> = charactersRepository.getCharacters(url)
}