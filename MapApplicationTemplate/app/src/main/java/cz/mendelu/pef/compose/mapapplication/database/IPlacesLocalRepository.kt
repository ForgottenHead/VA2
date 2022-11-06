package cz.mendelu.pef.compose.mapapplication.database

import kotlinx.coroutines.flow.Flow

interface IPlacesLocalRepository {
    fun getAll(): Flow<List<Place>>
    suspend fun insert(places: List<Place>)
}

