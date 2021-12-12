package com.keepcoding.imgram.mappers.presentation

import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.presentation.TvShowPresentation
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.presentation.MovieDetailPresentation
import com.keepcoding.imgram.model.presentation.MoviePresentation
import javax.inject.Inject

class MovieDetailPresentationMapper @Inject constructor() {

//    fun mapDataToPresentationDetail(data: List<MovieItemData>): List<MoviePresentation> {
//        return data.map { mapDataToPresentationDetail(it) }
//    }

    fun mapDataToPresentationDetail(data: MovieDetailItemData): MovieDetailPresentation {
        return MovieDetailPresentation(data.id, data.title, data.posterPath, data.vote_count, data.overview)
    }

}