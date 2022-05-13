package com.example.tmdb_17hw.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_17hw.data.Result
import com.example.tmdb_17hw.data.database.model.Film
import com.example.tmdb_17hw.data.repository.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val filmRepository: FilmRepository):ViewModel() {

    val searchResult = MutableStateFlow<Result<MutableList<Film>>>(Result.Loading(mutableListOf()))

    fun getSearchFilm(query:String) {
        viewModelScope.launch {
            val response = filmRepository.getSearchFilm(query)
            if (response.isSuccessful) {
                searchResult.value = response
            }
        }
    }

}