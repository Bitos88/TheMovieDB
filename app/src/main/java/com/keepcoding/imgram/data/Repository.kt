package com.keepcoding.imgram.data

import android.util.Log
import com.keepcoding.imgram.data.local.LocalDataSource
import com.keepcoding.imgram.data.remote.RemoteDataSource
import com.keepcoding.imgram.mappers.data.MovieDataMapper
import com.keepcoding.imgram.mappers.data.MovieDetailDataMapper
import com.keepcoding.imgram.mappers.data.MovieSimilarDataMapper
import com.keepcoding.imgram.mappers.data.TvShowDataMapper
import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.data.MovieDetailItemData
import com.keepcoding.imgram.model.data.MovieItemData
import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.network.MovieDetailItemNetworkData
import com.keepcoding.imgram.model.network.MovieItemNetworkData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val tvShowDataMapper: TvShowDataMapper,
    private val movieDataMapper: MovieDataMapper,
    private val movieDetailDataMapper: MovieDetailDataMapper,
    private val movieSimilarDataMapper: MovieSimilarDataMapper,
) {
//class Repository() {
//
//    private val remoteDataSource = RemoteDataSource()
//    private val localDataSource = LocalDataSource()

    private suspend fun getRemoteTvShows(): List<TvShowItemNetworkData> {
        val remoteResult = remoteDataSource.getTopShows()
        Log.d("Repository", "Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getRemotePopularMovies(): List<MovieItemNetworkData> {
        val remoteResult = remoteDataSource.getPopularMovies()
        Log.d("Repository", "Resultados remotos: ${remoteResult.size}")
        return remoteResult
    }

    private suspend fun getRemoteMovieSimilar(id: Long) : List<MovieDetailItemNetworkData> {
        val remoteResult = remoteDataSource.getMovieSimilar(id)
        return remoteResult

    }

    private suspend fun getLocalTvShows(): List<TvShowItemLocalData> {
        val localResult = localDataSource.getTvShows()
        Log.d("Repository", "Resultados locales: ${localResult.size}")
        return localResult
    }

    private suspend fun getRemoteMovieDetails(id: Long) : MovieDetailItemNetworkData {
        val remoteResult = remoteDataSource.getMovieDetails(id)
        return remoteResult
    }

    suspend fun getMovieSimilar(id: Long) : List<MovieDetailItemData> {
        val result = movieSimilarDataMapper.mapNetworkToData(getRemoteMovieSimilar(id))
        return result

    }

    suspend fun getMovieDetails(id: Long) : MovieDetailItemData {
        val result = movieDetailDataMapper.mapNetworkToDataDetail(getRemoteMovieDetails(id))
        return result
    }

    suspend fun getTvShows(): List<TvShowItemData> {
        val result = arrayListOf<TvShowItemData>()
        result.addAll(tvShowDataMapper.mapLocalToData(getLocalTvShows()))

        if (result.isEmpty()) {
            val remote = tvShowDataMapper.mapNetworkToData(getRemoteTvShows())
            localDataSource.insertTvShows(tvShowDataMapper.mapDataToLocal(remote))
            result.addAll(tvShowDataMapper.mapLocalToData(getLocalTvShows()))
        }

        return result
    }

    suspend fun getPopularMovies(): List<MovieItemData> {
        val result = arrayListOf<MovieItemData>()
        val remote = movieDataMapper.mapNetworkToData(getRemotePopularMovies())
        result.addAll(remote)

        return result
    }

    suspend fun deleteTvShow(itemData: TvShowItemData) {
        localDataSource.deleteTvShow(tvShowDataMapper.mapDataToLocal(itemData))
    }

    suspend fun deleteTvShowById(id: Long) {
        localDataSource.deleteTvShowById(id)
    }

    suspend fun deleteAllTvShows() {
        localDataSource.deleteAllTvShowS()
    }

    suspend fun getTvShow(id: Long): TvShowItemData {
        return localDataSource.getTvShows()
            .run(tvShowDataMapper::mapLocalToData)
            .find { it.id == id }!!
    }

    suspend fun insertTvShow(tvShow: TvShowItemData) {
        val tvShowItemLocalData = tvShow.run { tvShowDataMapper.mapDataToLocal(this) }
        localDataSource.insertTvShow(tvShowItemLocalData)
    }
}