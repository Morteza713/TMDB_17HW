package com.example.tmdb_17hw.ui.favorite

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
class FavoriteViewModel @Inject constructor(private val filmRepository: FilmRepository):ViewModel() {

    val favoriteFilmList = MutableStateFlow<Result<MutableList<Film>>>(Result.Success(mutableListOf()))

    fun getPopularFilm() {
        viewModelScope.launch {
            val response = filmRepository.getFavoriteFilm()
            favoriteFilmList.value = response
        }
    }
}