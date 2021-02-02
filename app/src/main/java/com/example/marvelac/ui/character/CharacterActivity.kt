package com.example.marvelac.ui.character

import Constants.Companion.CHARACTER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import app
import com.example.marvelac.databinding.ActivityCharacterBinding
import com.example.marvelac.repositories.MarvelRepository
import com.example.marvelac.ui.character.CharacterViewModel.UiModel
import com.example.marvelac.ui.character.CharacterViewModel.UiModel.*
import loadUrl

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var adapterSeries: SeriesAdapter
    private lateinit var adapterComics: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterViewModel = ViewModelProvider(this, CharacterViewModelFactory(MarvelRepository(app), intent.getLongExtra(CHARACTER, 0))).get()
        characterViewModel.character.observe(this, Observer (::UpdateUI))
        characterViewModel.series.observe(this, Observer (::UpdateUI))
        characterViewModel.comics.observe(this, Observer (::UpdateUI))
        adapterSeries = SeriesAdapter()
        adapterComics = ComicsAdapter()
        binding.recyclerViewSeries.adapter = adapterSeries
        binding.recyclerViewComics.adapter = adapterComics
    }

    private fun UpdateUI(model : UiModel){
        binding.progress.visibility = if(model is Loading) View.VISIBLE else View.GONE
        when(model){
            is ContentCharacter -> {
                binding.characterNameToolbar.title = model.character.name
                binding.characterInfo.text = model.character.description
                binding.characterImage.loadUrl(model.character.imageURL)
            }
            is ContentSeries -> {
                binding.textViewSeries.visibility = if(!model.series.isEmpty()) View.VISIBLE else View.GONE
                adapterSeries.series = model.series
            }
            is ContentComics -> {
                binding.textViewComics.visibility = if(!model.comics.isEmpty()) View.VISIBLE else View.GONE
                adapterComics.comics = model.comics
            }
        }

    }
}
