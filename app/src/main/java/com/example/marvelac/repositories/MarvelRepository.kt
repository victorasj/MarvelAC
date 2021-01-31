package com.example.marvelac.repositories

import com.example.marvelac.MarvelApp
import com.example.marvelac.model.CharacterData
import com.example.marvelac.model.CharacterDb
import com.example.marvelac.model.server.RetrofitInstance
import com.example.marvelac.ui.common.hashcreate
import com.example.marvelac.ui.common.timeStampCreate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MarvelRepository(application: MarvelApp) {

    private val db = application.db

    val public_key = "be8c856627468186e6cbdb2d680456d1"
    val private_key = "5c80fdde34561bc736abb4d67d5f3e6db2c237ca"


    suspend fun findCharacters() : List<CharacterDb> = withContext(Dispatchers.IO){
        val timestampString = timeStampCreate()
        val hashKey = hashcreate(timestampString, public_key, private_key)


        with(db.marvelDao()){
            if(getCountCharacter() <= 0){
                val characters =
                    RetrofitInstance.SERVICE
                    .getCharacters("characters?ts=$timestampString&apikey=$public_key&hash=$hashKey", 100, 0).data.results
                insertCharacters(characters.map(CharacterData::convertToDbCharacter))
            }

            getCharacters()
        }
    }

    fun createCharacters() : List<CharacterDb> = listOf(
            CharacterDb(1, "Lobezno", "Personaje con garras", "https://i1.whakoom.com/avatar/05/37/1f5e97398108472a947a137220dba9d2.jpg"),
            CharacterDb(2, "Lobezna", "Personaje con garras", "https://i1.whakoom.com/avatar/05/37/1f5e97398108472a947a137220dba9d2.jpg"),
            CharacterDb(3, "Otro", "Personaje con garras", "https://i1.whakoom.com/avatar/05/37/1f5e97398108472a947a137220dba9d2.jpg"),
            CharacterDb(4, "Otra", "Personaje con garras", "https://i1.whakoom.com/avatar/05/37/1f5e97398108472a947a137220dba9d2.jpg"),
            CharacterDb(5, "Loca", "Personaje con garras", "https://i1.whakoom.com/avatar/05/37/1f5e97398108472a947a137220dba9d2.jpg")
    )

}

private fun CharacterData.convertToDbCharacter() = CharacterDb(
    id,
    name,
    description,
    image?.path + "/portrait_medium." + image?.extension
)