package com.example.marvelac.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app
import com.example.data.repository.CharactersRepository
import com.example.interactor.GetCharacters
import com.example.marvelac.EventObserver
import com.example.marvelac.data.database.RoomDataSource
import com.example.marvelac.data.server.MarvelDataSource
import com.example.marvelac.databinding.FragmentMainBinding
import getViewModel

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    private lateinit var mainViewModel : MainViewModel
    private lateinit var adapter: CharactersAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        mainViewModel = getViewModel { MainViewModel(GetCharacters(CharactersRepository(RoomDataSource(app.db), MarvelDataSource()))) }
        adapter = CharactersAdapter(mainViewModel::onCharacterClick)
        binding.recyclerviewCharacter.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerviewCharacter.adapter = adapter
        mainViewModel.characters.observe(viewLifecycleOwner, Observer (::updateUi))
        mainViewModel.navigateToCharacter.observe(viewLifecycleOwner, EventObserver {
            val action = MainFragmentDirections.actionMainFragmentToCharacterFragment(it.id)
            navController.navigate(action)
        })
    }

    private fun updateUi(model : MainViewModel.UiModel) {
        binding.progress.visibility = if(model is MainViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when(model) {
            is MainViewModel.UiModel.Content -> adapter.characters = model.characters
        }
    }

}