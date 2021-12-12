package com.keepcoding.imgram.mappers.presentation

import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.presentation.MovieDetailPresentation
import com.keepcoding.imgram.model.presentation.TvShowPresentation
import javax.inject.Inject

class MovieSimilarPresentationMapper @Inject constructor() {

    fun mapDataToPresentation(data: List<MovieDetailItemData>): List<MovieDetailPresentation> {
        return data.map { mapDataToPresentation(it) }
    }

    fun mapDataToPresentation(data: MovieDetailItemData): MovieDetailPresentation {
        return MovieDetailPresentation(data.id, data.title, data.posterPath, data.vote_count, data.overview)
    }

//    fun mapPresentationToData(presentation: TvShowPresentation): TvShowItemData {
//        return TvShowItemData(presentation.id, presentation.name, presentation.posterPath)
//    }
}