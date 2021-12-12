package com.keepcoding.imgram.data.remote

import com.keepcoding.imgram.model.PagedResultData
import com.keepcoding.imgram.model.network.MovieDetailItemNetworkData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBApi {

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTvShows(): PagedResultData<TvShowItemNetworkData>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): PagedResultData<MovieItemNetworkData>

    @GET("/3/movie/{movie_id}")
    suspend fun getMoviesDetails(@Path("movie_id") id: Long): MovieDetailItemNetworkData

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getMoviesSimilar(@Path("movie_id") id: Long): PagedResultData<MovieDetailItemNetworkData>


}
