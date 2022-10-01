package cz.mendelu.pef.compose.petstore.communication

import cz.mendelu.pef.compose.petstore.architecture.CommunicationResult
import cz.mendelu.pef.compose.petstore.architecture.IBaseRemoteRepository
import cz.mendelu.pef.compose.petstore.models.Pet
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface IPetsRemoteRepository : IBaseRemoteRepository{
    suspend fun pets(status: String): CommunicationResult<List<Pet>>
    suspend fun pet(id: Long): CommunicationResult<Pet>
    suspend fun deletePet(id: Long): CommunicationResult<ResponseBody>
}