package repositories

import android.app.Activity
import model.CharacterDb

class CharactersRepository(activity : Activity) {

    suspend fun findCharacters() = emptyList<CharacterDb>()
}