package com.nurmamedova.searchapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurmamedova.searchapp.data.models.MovieDescriptionModel
import com.nurmamedova.searchapp.domain.interactors.MovieDescriptionInteractor
import com.nurmamedova.searchapp.data.utils.StatusResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDescriptionViewModel @Inject constructor(
    private val interactor: MovieDescriptionInteractor
) : ViewModel() {

    private var _movieDescriptionResponse =
        MutableLiveData<StatusResponse<MovieDescriptionModel>>()
    val movieDescriptionResponse: LiveData<StatusResponse<MovieDescriptionModel>> =
        _movieDescriptionResponse

    fun fetchMovieDescription(movieId: Int) {
        viewModelScope.launch {
            interactor.getMovieDescription(movieId).collect {
                _movieDescriptionResponse.postValue(it)
            }
        }
    }

}