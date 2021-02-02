package com.example.marvelac.ui.main

import Constants.Companion.CHARACTER
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import app
import com.example.marvelac.databinding.ActivityMainBinding
import com.example.marvelac.repositories.MarvelRepository
import com.example.marvelac.ui.character.CharacterActivity
import startActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(MarvelRepository(app))).get()
        adapter = CharactersAdapter(mainViewModel::onCharacterClick)
        binding.recyclerviewCharacter.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerviewCharacter.adapter = adapter
        mainViewModel.characters.observe(this, Observer (::updateUi))
    }

    private fun updateUi(model : MainViewModel.UiModel) {
        binding.progress.visibility = if(model is MainViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when(model) {
            is MainViewModel.UiModel.Content -> adapter.characters = model.characters
            is MainViewModel.UiModel.Navigation -> startActivity<CharacterActivity> { putExtra(CHARACTER, model.character.id) }
        }
    }

}
