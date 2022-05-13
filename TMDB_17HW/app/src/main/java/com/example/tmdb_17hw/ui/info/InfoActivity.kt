package com.example.tmdb_17hw.ui.info

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tmdb_17hw.data.network.model.MovieDetail
import com.example.tmdb_17hw.databinding.ActivityMainBinding
import com.example.tmdb_17hw.databinding.InfoActivityBinding
import com.example.tmdb_17hw.ui.exoplayer.Player
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoActivity:AppCompatActivity() {

    private var _binding: InfoActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<InfoViewModel>()
    private lateinit var filmState: MovieDetail

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = InfoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(filmState.id.toString(),0)
        val title = intent.getStringExtra(filmState.title)

        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel.film.observe(this){movie->
            binding.tvGenre.text = "Genre : ${movie.genres.joinToString(" , ") { it.name }}"
            binding.tvDecs.text = "Description : ${movie.overview}"
            binding.tvTitle.text = "Title : ${movie.title}"
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .into(binding.ivFilm)
            binding.btnPlay.visibility = View.VISIBLE
        }
        binding.btnPlay.visibility = View.GONE
        binding.btnPlay.setOnClickListener {
            val intent= Intent  (this,Player::class.java)
            startActivity(intent)
        }
        viewModel.getFilmDetails(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}