package com.example.marvelac.model.server

import com.example.marvelac.model.CharacterComicsDataWrapper
import com.example.marvelac.model.CharacterDataWrapper
import com.example.marvelac.model.CharacterSeriesDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelApi {

    @GET
    suspend fun getCharacters(
        @Url url : String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int): CharacterDataWrapper

    @GET
    suspend fun getDataCharacter(
        @Url url : String) : CharacterDataWrapper

    @GET
    suspend fun getCharacterComics(@Url url : String) : CharacterComicsDataWrapper

    @GET
    suspend fun getCharacterSeries(@Url url : String) : CharacterSeriesDataWrapper

}