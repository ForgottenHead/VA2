package cz.mendelu.pef.compose.mapapplication.database

import androidx.room.*
import cz.mendelu.pef.compose.mapapplication.database.Place
import kotlinx.coroutines.flow.Flow

@Dao
interface PlacesDao {
    @Query("SELECT * FROM places")
    fun getAll(): Flow<List<Place>>

    @Insert
    suspend fun insert(places: List<Place>)

}
