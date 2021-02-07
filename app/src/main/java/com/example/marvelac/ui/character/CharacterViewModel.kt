package com.example.marvelac.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.Character
import com.example.domain.CharacterComic
import com.example.domain.CharacterSerie
import com.example.interactor.GetCharacterById
import com.example.interactor.GetCharacterComics
import com.example.interactor.GetCharacterSeries
import com.example.marvelac.ui.common.ScopedViewModel
import com.example.marvelac.ui.common.createURLCharacter
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterById : GetCharacterById,
    private val getCharacterSeries: GetCharacterSeries,
    private val getCharacterComics: GetCharacterComics,
    private val characterId: Long) : ScopedViewModel() {

    sealed class UiModel {
        object Loading : UiModel()
        class ContentCharacter(val character : Character) : UiModel()
        class ContentSeries(val series : List<CharacterSerie>) : UiModel()
        class ContentComics(val comics : List<CharacterComic>) : UiModel()
    }

    private val _character = MutableLiveData<UiModel>()
    val character : LiveData<UiModel>
        get() {
            if(_character.value == null) loadCharacter(characterId)
            return _character
        }

    private val _series = MutableLiveData<UiModel>()
    val series : LiveData<UiModel>
        get() {
            if(_series.value == null) loadCharacterSeries(characterId, createURLCharacter("series", characterId))
            return _series
        }

    private val _comics = MutableLiveData<UiModel>()
    val comics : LiveData<UiModel>
        get() {
            if(_comics.value == null) loadCharacterComics(characterId, createURLCharacter("comics", characterId))
            return _comics
        }

    private fun loadCharacter(characterId : Long){
        launch {
            _character.value = UiModel.Loading
            _character.value = UiModel.ContentCharacter(getCharacterById.invoke(characterId))
        }
    }

    private fun loadCharacterSeries(characterId : Long, url : String){
        launch {
            _series.value = UiModel.Loading
            _series.value = UiModel.ContentSeries(getCharacterSeries.invoke(characterId, url))
        }
    }

    private fun loadCharacterComics(characterId : Long, url : String){
        launch {
            _comics.value = UiModel.Loading
            _comics.value = UiModel.ContentComics(getCharacterComics.invoke(characterId, url))
        }
    }

}

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory( private val getCharacterById : GetCharacterById,
                                 private val getCharacterSeries: GetCharacterSeries,
                                 private val getCharacterComics: GetCharacterComics,
                                 private  val characterId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = CharacterViewModel(getCharacterById, getCharacterSeries,  getCharacterComics, characterId) as T
}