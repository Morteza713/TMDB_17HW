package com.example.tmdb_17hw.ui.comingsoon

import android.annotation.SuppressLint
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
import com.example.tmdb_17hw.databinding.ComingsoonFragmentBinding
import com.example.tmdb_17hw.ui.FilmAdapter
import com.example.tmdb_17hw.ui.info.InfoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComingSoonFragment():Fragment(R.layout.comingsoon_fragment) {

    private var _binding: ComingsoonFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ComingSoonViewModel by viewModels()
    private lateinit var filmState: MovieDetail

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ComingsoonFragmentBinding.bind(view)

        val adapter = FilmAdapter() { id, title ->
            val intent = Intent(requireActivity(), InfoActivity::class.java).apply {
                putExtra(filmState.id.toString(), id)
                putExtra(filmState.title, title)
            }
            startActivity(intent)
        }

        binding.rvComingSoon.adapter = adapter
        binding.rvComingSoon.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.comingSoonFilm.collect{result->
                when(result){
                    is Result.Success->{
                        adapter.submitList(result.data!!)
                        adapter.notifyDataSetChanged()
                    }
                    is Result.Loading->{
                        Log.d("ComingSoon" , "Loading")
                    }
                    is Result.Error->{
                        Log.d("ComingSoon" , "Error")
                        }
                    }
                }
            }
        viewModel.getComingSoonFilm()
}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}