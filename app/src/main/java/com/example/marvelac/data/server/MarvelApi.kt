package com.example.marvelac.data.server

import com.example.marvelac.data.CharacterComicsDataWrapper
import com.example.marvelac.data.CharacterDataWrapper
import com.example.marvelac.data.CharacterSeriesDataWrapper
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
    suspend fun getCharacterComics(@Url url : String) : CharacterComicsDataWrapper

    @GET
    suspend fun getCharacterSeries(@Url url : String) : CharacterSeriesDataWrapper

}