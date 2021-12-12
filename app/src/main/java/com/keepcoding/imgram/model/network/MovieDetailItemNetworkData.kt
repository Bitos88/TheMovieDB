package com.keepcoding.imgram.model.network

import com.squareup.moshi.Json

data class MovieDetailItemNetworkData(
    @Json(name = "id") var id: Long?,
    @Json(name = "title") var title: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "vote_count") var vote_count: Int?,
    @Json(name = "overview") var overview: String?,
)