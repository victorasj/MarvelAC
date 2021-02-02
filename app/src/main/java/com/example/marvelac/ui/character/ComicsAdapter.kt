package com.example.marvelac.ui.character

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import basicDiffUtil
import com.example.marvelac.R
import com.example.marvelac.databinding.ComicViewBinding
import com.example.marvelac.databinding.SerieViewBinding
import com.example.marvelac.model.CharacterComicDb
import inflate
import loadUrl

class ComicsAdapter() : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    var comics : List<CharacterComicDb> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.comic_view, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = comics[position]
        holder.bind(comic)
    }

    override fun getItemCount(): Int = comics.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = ComicViewBinding.bind(view)
        fun bind(comic : CharacterComicDb) = with(binding){
            comicName.text = comic.name
            comic.imageURL.let { comicImage.loadUrl(comic.imageURL) }
        }
    }
}

