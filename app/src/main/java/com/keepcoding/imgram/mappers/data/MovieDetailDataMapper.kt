package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.network.MovieDetailItemNetworkData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import javax.inject.Inject

class MovieDetailDataMapper @Inject constructor() {

    fun mapNetworkToDataDetail(network: MovieDetailItemNetworkData): MovieDetailItemData {
        return MovieDetailItemData(network.id, network.title, network.posterPath, network.vote_count, network.overview)
    }

//    fun mapNetworkToDataDetail(networkList: List<MovieItemNetworkData>): List<MovieItemData> {
//        return networkList.map { mapNetworkToDataDetail(it) }
//    }
}