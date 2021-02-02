package com.example.marvelac.ui.character

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import basicDiffUtil
import com.example.marvelac.R
import com.example.marvelac.databinding.SerieViewBinding
import com.example.marvelac.model.CharacterSerieDb
import inflate
import loadUrl

class SeriesAdapter() : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    var series : List<CharacterSerieDb> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.serie_view, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val serie = series[position]
        holder.bind(serie)
    }

    override fun getItemCount(): Int = series.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val binding = SerieViewBinding.bind(view)
        fun bind(serie : CharacterSerieDb) = with(binding){
            serieName.text = serie.name
            serie.imageURL.let { serieImage.loadUrl(serie.imageURL) }
        }
    }
}

