package com.example.interactor

import com.example.data.repository.CharactersRepository
import com.example.domain.Character

class GetCharacterById(private val charactersRepository: CharactersRepository) {
    suspend fun invoke(characterById : Long) : Character = charactersRepository.getCharacter(characterById)
}