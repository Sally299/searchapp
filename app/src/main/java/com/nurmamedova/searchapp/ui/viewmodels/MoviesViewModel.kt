package com.nurmamedova.searchapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurmamedova.searchapp.data.models.MovieModel
import com.nurmamedova.searchapp.domain.interactors.MoviesInteractor
import com.nurmamedova.searchapp.data.utils.StatusResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private var _movieModelResponse = MutableLiveData<StatusResponse<List<MovieModel>>>()
    val movieModelResponse: LiveData<StatusResponse<List<MovieModel>>> = _movieModelResponse

    init {
        fetchAllMovies()
    }

    private fun fetchAllMovies() {
        viewModelScope.launch {
            moviesInteractor.getMovies().collect {
                _movieModelResponse.postValue(it)
            }
        }
    }


    fun updateMovieStatus(movieId: Int) {
        when (_movieModelResponse.value as StatusResponse<List<MovieModel>>) {
            is StatusResponse.Success -> {
                (_movieModelResponse.value as StatusResponse.Success<List<MovieModel>>)
                    .data.first {
                        it.movieId == movieId
                    }
            }

            else -> {}
        }
    }


}