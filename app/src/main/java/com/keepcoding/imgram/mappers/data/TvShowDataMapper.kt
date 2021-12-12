package com.keepcoding.imgram.mappers.data

import com.keepcoding.imgram.model.data.TvShowItemData
import com.keepcoding.imgram.model.TvShowItemLocalData
import com.keepcoding.imgram.model.network.TvShowItemNetworkData
import javax.inject.Inject

class TvShowDataMapper @Inject constructor() {

    //OBTENER DE LA BASE DE DATOS
    private fun mapLocalToData(local: TvShowItemLocalData): TvShowItemData {
        return TvShowItemData(local.id, local.name, local.posterPath, local.favorite)
    }

    fun mapLocalToData(localList: List<TvShowItemLocalData>): List<TvShowItemData> {
        return localList.map { mapLocalToData(it) }
    }

    //INSERT -- done
    fun mapDataToLocal(local: TvShowItemData): TvShowItemLocalData {
        return TvShowItemLocalData(local.id, local.name, local.posterPath, local.favorite)
    }

    fun mapDataToLocal(localList: List<TvShowItemData>): List<TvShowItemLocalData> {
        return localList.map { mapDataToLocal(it) }
    }

    //OBTENER INFORMACION DE LA API -- done
    private fun mapNetworkToData(local: TvShowItemNetworkData): TvShowItemData {
        return TvShowItemData(local.id, local.name, local.posterPath)
    }

    fun mapNetworkToData(localList: List<TvShowItemNetworkData>): List<TvShowItemData> {
        return localList.map { mapNetworkToData(it) }
    }
}