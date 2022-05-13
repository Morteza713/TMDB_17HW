package com.example.tmdb_17hw.ui.comingsoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_17hw.data.database.model.Film
import com.example.tmdb_17hw.data.repository.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.tmdb_17hw.data.Result

@HiltViewModel
class ComingSoonViewModel @Inject constructor(private val filmRepository: FilmRepository):ViewModel() {
    val comingSoonFilm = MutableStateFlow<Result<MutableList<Film>>>(Result.success(mutableListOf()))

    fun getComingSoonFilm() {
        viewModelScope.launch {
            val response = filmRepository.getComingSoonFilm()
            if (response.isSuccessful){
                comingSoonFilm.value = response
            }
        }
    }
}