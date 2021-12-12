package com.keepcoding.imgram.model.data

import com.squareup.moshi.Json

data class MovieDetailItemData(
    var id: Long?,
    var title: String?,
    var posterPath: String?,
    var vote_count: Int?,
    var overview: String?,
)