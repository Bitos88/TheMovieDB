package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.network.MovieDetailItemNetworkData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import javax.inject.Inject

class MovieSimilarDataMapper @Inject constructor() {

    private fun mapNetworkToData(local: MovieDetailItemNetworkData): MovieDetailItemData {
        return MovieDetailItemData(local.id, local.title, local.posterPath, local.vote_count, local.overview)
    }

    fun mapNetworkToData(localList: List<MovieDetailItemNetworkData>): List<MovieDetailItemData> {
        return localList.map { mapNetworkToData(it) }
    }
}