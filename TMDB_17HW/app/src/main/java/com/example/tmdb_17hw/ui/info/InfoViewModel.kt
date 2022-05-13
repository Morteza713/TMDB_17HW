package com.example.tmdb_17hw.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_17hw.data.network.model.MovieDetail
import com.example.tmdb_17hw.data.repository.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(private val filmRepository: FilmRepository):ViewModel() {

    val film : MutableLiveData<MovieDetail> by lazy { MutableLiveData() }

    fun getFilmDetails(id:Int) {
        viewModelScope.launch {
            val response = filmRepository.getFilmDetails(id)
            if (response.isSuccessful)
                film.postValue(response.body()!!)
        }
    }
}