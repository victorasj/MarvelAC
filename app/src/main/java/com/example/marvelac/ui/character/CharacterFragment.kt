package com.example.marvelac.ui.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import app
import com.example.data.repository.CharactersRepository
import com.example.interactor.GetCharacterById
import com.example.interactor.GetCharacterComics
import com.example.interactor.GetCharacterSeries
import com.example.marvelac.data.database.RoomDataSource
import com.example.marvelac.data.server.MarvelDataSource
import com.example.marvelac.databinding.FragmentCharacterBinding
import loadUrl
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterFragment : ScopeFragment() {

    private lateinit var binding : FragmentCharacterBinding

    private lateinit var adapterSeries: SeriesAdapter
    private lateinit var adapterComics: ComicsAdapter

    private val args: CharacterFragmentArgs by navArgs()

    private val characterViewModel: CharacterViewModel by viewModel {
        parametersOf(args.characterId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*roomDataSource = RoomDataSource(app.db)
        charactersRepository = CharactersRepository(roomDataSource, MarvelDataSource())
        characterViewModel = ViewModelProvider(this,
            CharacterViewModelFactory(
                GetCharacterById(charactersRepository),
                GetCharacterSeries(charactersRepository),
                GetCharacterComics(charactersRepository),
                args.characterId))
            .get()*/

        characterViewModel.character.observe(viewLifecycleOwner, Observer (::UpdateUI))
        characterViewModel.series.observe(viewLifecycleOwner, Observer (::UpdateUI))
        characterViewModel.comics.observe(viewLifecycleOwner, Observer (::UpdateUI))
        adapterSeries = SeriesAdapter()
        adapterComics = ComicsAdapter()
        binding.recyclerViewSeries.adapter = adapterSeries
        binding.recyclerViewComics.adapter = adapterComics
    }


    private fun UpdateUI(model : CharacterViewModel.UiModel){
        when(model){
            is CharacterViewModel.UiModel.ContentCharacter -> {
                binding.characterNameToolbar.title = model.character.name
                binding.characterInfo.text = model.character.description
                binding.characterImage.loadUrl(model.character.imageURL)
            }
            is CharacterViewModel.UiModel.ContentSeries -> {
                binding.textViewSeries.visibility = if(!model.series.isEmpty()) View.VISIBLE else View.GONE
                adapterSeries.series = model.series
            }
            is CharacterViewModel.UiModel.ContentComics -> {
                binding.textViewComics.visibility = if(!model.comics.isEmpty()) View.VISIBLE else View.GONE
                adapterComics.comics = model.comics
            }
        }

    }

}