package com.example.marvelac.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interactor.GetCharacters
import com.example.marvelac.Event
import com.example.marvelac.ui.common.ScopedViewModel
import kotlinx.coroutines.launch
import com.example.domain.Character
import com.example.marvelac.ui.common.createURLCharacter

class MainViewModel(private val getCharacters: GetCharacters) : ScopedViewModel() {



    sealed class UiModel {
        object Loading : UiModel()
        class Content(val characters : List<Character>) : UiModel()
    }

    private val _characters = MutableLiveData<UiModel>()
    val characters : LiveData<UiModel>
        get() {
            if(_characters.value == null) refresh()
            return _characters
        }

    private val _navigateToCharacter = MutableLiveData<Event<Character>>()
    val navigateToCharacter: LiveData<Event<Character>> get() = _navigateToCharacter


    private fun refresh() {
        launch {
            _characters.value = UiModel.Loading
            _characters.value = UiModel.Content(getCharacters.invoke(createURLCharacter()))
        }
    }

    fun onCharacterClick(character: Character) {
        _navigateToCharacter.value = Event(character)
    }


}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val getCharacters: GetCharacters) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MainViewModel(getCharacters) as T
}