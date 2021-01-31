package com.example.marvelac.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelac.model.CharacterDb
import com.example.marvelac.repositories.MarvelRepository
import kotlinx.coroutines.launch
import ui.common.Scope
import ui.common.Scope.Impl

class MainViewModel(private val repository: MarvelRepository) : ViewModel(), Scope by Impl() {

    init {
        initScope()
    }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val characters : List<CharacterDb>) : UiModel()
        class Navigation(val character : CharacterDb) : UiModel()
    }

    private val _characters = MutableLiveData<UiModel>()
    val characters : LiveData<UiModel>
        get() {
            if(_characters.value == null) refresh()
            return _characters
        }


    private fun refresh() {
        launch {
            _characters.value = UiModel.Loading
            _characters.value = UiModel.Content(repository.findCharacters())
        }
    }

    fun onCharacterClick(character: CharacterDb) {
        _characters.value = UiModel.Navigation(character)
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val marvelRepository: MarvelRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(marvelRepository) as T
}