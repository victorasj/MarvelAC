package com.example.marvelac.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelac.model.CharacterComicDb
import com.example.marvelac.model.CharacterDb
import com.example.marvelac.model.CharacterSerieDb
import com.example.marvelac.repositories.MarvelRepository
import com.example.marvelac.ui.main.MainViewModel
import kotlinx.coroutines.launch
import ui.common.Scope

class CharacterViewModel(private val repository: MarvelRepository, private val characterId: Long) : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    sealed class UiModel {
        object Loading : UiModel()
        class ContentCharacter(val character : CharacterDb) : UiModel()
        class ContentSeries(val series : List<CharacterSerieDb>) : UiModel()
        class ContentComics(val comics : List<CharacterComicDb>) : UiModel()
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
            if(_series.value == null) loadCharacterSeries(characterId)
            return _series
        }

    private val _comics = MutableLiveData<UiModel>()
    val comics : LiveData<UiModel>
        get() {
            if(_comics.value == null) loadCharacterComics(characterId)
            return _comics
        }

    private fun loadCharacter(characterId : Long){
        launch {
            _character.value = UiModel.Loading
            _character.value = UiModel.ContentCharacter(repository.findCharacter(characterId))
        }
    }

    private fun loadCharacterSeries(characterId : Long){
        launch {
            _series.value = UiModel.Loading
            _series.value = UiModel.ContentSeries(repository.findSeriesCharacter(characterId))
        }
    }

    private fun loadCharacterComics(characterId : Long){
        launch {
            _comics.value = UiModel.Loading
            _comics.value = UiModel.ContentComics(repository.findComicsCharacter(characterId))
        }
    }




    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory(private val marvelRepository: MarvelRepository, private  val characterId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = CharacterViewModel(marvelRepository, characterId) as T
}