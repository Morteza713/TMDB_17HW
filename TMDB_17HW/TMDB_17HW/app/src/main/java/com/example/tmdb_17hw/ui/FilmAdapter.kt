package com.example.tmdb_17hw.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb_17hw.data.database.model.Film
import com.example.tmdb_17hw.databinding.FilmItemBinding

class FilmAdapter(private val cLickListener:(id:Int,title:String) -> Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val filmList : MutableList<Film> = mutableListOf()

    class FilmViewHolder(private val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(film: Film){
            binding.tvTitle.text = "Title : ${film.title}"
            binding.tvDecs.text = "Description : ${film.overview}"
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${film.poster_path}")
                .into(binding.ivPic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(FilmItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FilmViewHolder ->{
                holder.bind(filmList[position])
                holder.itemView.setOnClickListener{
                    cLickListener(filmList[position].id,filmList[position].title!!)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }
    fun submitList(films:MutableList<Film>){
        filmList.clear()
        filmList.addAll(films)
    }
}