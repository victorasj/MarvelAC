package ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelac.R
import com.example.marvelac.databinding.CharacterViewBinding
import inflate
import loadUrl
import model.CharacterDb

class CharactersAdapter(val listener: (CharacterDb) -> Unit) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    var characters : List<CharacterDb> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.character_view, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
        holder.itemView.setOnClickListener { listener(character) }
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterViewBinding.bind(view)
        fun bind(character : CharacterDb) = with(binding){
            characterName.text = character.name
            characterImage.loadUrl(character.imageURL)
        }
    }
}
