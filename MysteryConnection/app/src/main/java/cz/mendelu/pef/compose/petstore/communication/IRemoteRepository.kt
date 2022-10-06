package cz.mendelu.pef.compose.petstore.communication

import cz.mendelu.pef.compose.petstore.architecture.CommunicationResult
import cz.mendelu.pef.compose.petstore.architecture.IBaseRemoteRepository
import cz.mendelu.pef.compose.petstore.models.Data
import retrofit2.Response

// TODO dodělat
interface IRemoteRepository : IBaseRemoteRepository{
    suspend fun getItems(): CommunicationResult<Data>
}