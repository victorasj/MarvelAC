package com.example.marvelac.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import basicDiffUtil
import com.example.marvelac.R
import com.example.marvelac.databinding.CharacterViewBinding
import com.example.domain.Character
import inflate
import loadUrl

class CharactersAdapter(val listener: (Character) -> Unit) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    var characters : List<Character> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.character_view, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
        holder.itemView.setOnClickListener { listener(character) }
    }

    override fun getItemCount(): Int = characters.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = CharacterViewBinding.bind(view)
        fun bind(character : Character) = with(binding){
            characterName.text = character.name
            character.imageURL?.let { characterImage.loadUrl(it) }
        }
    }
}

