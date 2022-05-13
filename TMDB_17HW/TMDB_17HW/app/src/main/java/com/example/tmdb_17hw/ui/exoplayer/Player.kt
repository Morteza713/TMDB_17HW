package com.example.tmdb_17hw.ui.exoplayer

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tmdb_17hw.databinding.PlayActivityBinding
import com.example.tmdb_17hw.databinding.SearchFragmentBinding
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Player(): AppCompatActivity() {

    lateinit var player : SimpleExoPlayer
    private var _binding: PlayActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = PlayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "Player"
//        AdaptiveTrackSelection.Factory()
//        binding.exoPlayer

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}