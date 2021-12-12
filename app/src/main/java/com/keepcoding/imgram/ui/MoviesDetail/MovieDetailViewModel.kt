package com.keepcoding.imgram.ui.MoviesDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.imgram.data.Repository
import com.keepcoding.imgram.mappers.presentation.MovieDetailPresentationMapper
import com.keepcoding.imgram.mappers.presentation.MoviePresentationMapper
import com.keepcoding.imgram.mappers.presentation.MovieSimilarPresentationMapper
import com.keepcoding.imgram.model.presentation.MovieDetailPresentation
import com.keepcoding.imgram.model.presentation.MoviePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: Repository,
    private val mapper: MovieDetailPresentationMapper,
    private val similarMapper : MovieSimilarPresentationMapper
) : ViewModel() {

    private val _images: MutableLiveData<MovieDetailPresentation> by lazy {
        MutableLiveData<MovieDetailPresentation>()
    }
    val images: LiveData<MovieDetailPresentation> get() = _images

    private val _similar: MutableLiveData<List<MovieDetailPresentation>> by lazy {
        MutableLiveData<List<MovieDetailPresentation>>()
    }

    val similar : LiveData<List<MovieDetailPresentation>> get() = _similar


    //CREAR FUNCION ASYNC PARA LAS 2 COROUTINE

     fun getMoviesDetail(id: Long){
        viewModelScope.launch {
            val result = async(Dispatchers.IO) {
                getMovieSimilar(id)
                repository.getMovieDetails(id)

            }

            println("BITOS ${result}")
            _images.postValue(mapper.mapDataToPresentationDetail(result.await()))
        }
    }

    fun getMovieSimilar(id: Long) {
        viewModelScope.async {
            val result = withContext(Dispatchers.IO) {
                repository.getMovieSimilar(id)
            }
            _similar.postValue(similarMapper.mapDataToPresentation(result))
        }
    }
}