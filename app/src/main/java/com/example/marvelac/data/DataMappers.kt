package com.example.marvelac.data

import com.example.domain.Character as CharacterDomain
import com.example.domain.CharacterSerie as CharacterSerieDomain
import com.example.domain.CharacterComic as CharacterComicDomain
import com.example.marvelac.data.database.CharacterDb as CharacterRoom
import com.example.marvelac.data.database.CharacterSerieDb as CharacterSerieRoom
import com.example.marvelac.data.database.CharacterComicDb as CharacterComicRoom

fun CharacterDomain.toRoomCharacter() : CharacterRoom =
        CharacterRoom(
                id, name, description, imageURL
        )

fun CharacterRoom.toDomainCharacter() : CharacterDomain =
        CharacterDomain(
                id, name, description, imageURL
        )

fun CharacterSerieDomain.toRoomCharacterSerie() : CharacterSerieRoom =
        CharacterSerieRoom(
                id, name, imageURL, characterId
        )

fun CharacterSerieRoom.toDomainCharacterSerie() : CharacterSerieDomain =
        CharacterSerieDomain(
                id, name, imageURL, characterId
        )


fun CharacterComicDomain.toRoomCharacterComic() : CharacterComicRoom =
        CharacterComicRoom(
                id, name, imageURL, characterId
        )

fun CharacterComicRoom.toDomainCharacterComic() : CharacterComicDomain =
        CharacterComicDomain(
                id, name, imageURL, characterId
        )

fun CharacterData.convertToDomainCharacter() = CharacterDomain(
        id,
        name,
        description,
        image?.path + "/portrait_small." + image?.extension
)

fun CharacterSerie.convertToDomainCharacterSerie(characterId : Long) = CharacterSerieDomain(
        id,
        title,
        image?.path + "/portrait_small." + image?.extension,
        characterId
)

fun CharacterComic.convertToDomainCharacterComic(characterId : Long) = CharacterComicDomain(
        id,
        title,
        image?.path + "/portrait_small." + image?.extension,
        characterId
)
