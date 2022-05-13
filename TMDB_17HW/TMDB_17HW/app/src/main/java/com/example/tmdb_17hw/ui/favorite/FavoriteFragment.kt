package com.example.tmdb_17hw.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb_17hw.R
import com.example.tmdb_17hw.data.Result
import com.example.tmdb_17hw.data.network.model.MovieDetail
import com.example.tmdb_17hw.databinding.FavoriteFragmentBinding
import com.example.tmdb_17hw.ui.FilmAdapter
import com.example.tmdb_17hw.ui.comingsoon.ComingSoonViewModel
import com.example.tmdb_17hw.ui.info.InfoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment:Fragment(R.layout.favorite_fragment) {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FavoriteViewModel by viewModels()
    private lateinit var filmState: MovieDetail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FavoriteFragmentBinding.bind(view)

        val adapter = FilmAdapter{ id, title->
            val intent= Intent(requireActivity(), InfoActivity::class.java).apply {
                putExtra(filmState.id.toString(),id)
                putExtra(filmState.title,title)
            }
            startActivity(intent)
        }

        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.favoriteFilmList.collect{ state ->
                when(state){
                    is Result.Success->{
                        adapter.submitList(state.data!!)
                        adapter.notifyDataSetChanged()
                    }
                    is Result.Error->{
                        Log.d("Favorite" , "Error")
                    }
                    is Result.Loading->{
                        Log.d("Favorite" , "Loading")
                    }
                }
            }
        }

        viewModel.getPopularFilm()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}