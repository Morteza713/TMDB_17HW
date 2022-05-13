package com.example.tmdb_17hw.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdb_17hw.R
import com.example.tmdb_17hw.data.Result
import com.example.tmdb_17hw.data.network.model.MovieDetail
import com.example.tmdb_17hw.databinding.SearchFragmentBinding
import com.example.tmdb_17hw.ui.FilmAdapter
import com.example.tmdb_17hw.ui.comingsoon.ComingSoonViewModel
import com.example.tmdb_17hw.ui.info.InfoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment:Fragment(R.layout.search_fragment) {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel : SearchViewModel by viewModels()
    private lateinit var filmState: MovieDetail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SearchFragmentBinding.bind(view)

        val adapter = FilmAdapter(){ id, title->
            val intent= Intent(requireActivity(), InfoActivity::class.java).apply {
                putExtra(filmState.id.toString(),id)
                putExtra(filmState.title, title)
            }
            startActivity(intent)
        }
        binding.rvSearch.adapter = adapter
        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            viewModel.searchResult.collect{result->
                when(result){
                    is Result.Success-> {
                        if (result.data!!.size != 0) {
                            adapter.submitList(result.data)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    is Result.Error->{
                        Log.d("Search" , "Error")
                    }
                    is Result.Loading->{
                        Log.d("Search" , "Loading")
                    }
                }
            }
        }

        binding.btnSearch.setOnClickListener {
                viewModel.getSearchFilm(binding.edSearch.text.toString())
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}