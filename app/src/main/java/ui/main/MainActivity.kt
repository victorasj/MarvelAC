package ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.marvelac.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import repositories.CharactersRepository
import startActivity
import ui.character.CharacterActivity
import ui.main.MainViewModel.UiModel
import ui.main.MainViewModel.UiModel.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(CharactersRepository(this))).get()
        adapter = CharactersAdapter(mainViewModel::onCharacterClick)
        recyclerviewCharacter.adapter = adapter

        mainViewModel.characters.observe(this, Observer (::updateUi))
    }

    fun updateUi(model : UiModel) {
        when(model) {
            is Content -> adapter.characters = model.characters
            is Navigation -> startActivity<CharacterActivity> { putExtra("CharacterID", model.character) }
            Loading -> binding.progress.visibility = if(model is Loading) View.VISIBLE else View.GONE
        }
    }

}
