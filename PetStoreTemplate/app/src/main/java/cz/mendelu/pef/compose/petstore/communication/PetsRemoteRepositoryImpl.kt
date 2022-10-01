package cz.mendelu.pef.compose.petstore.communication

import cz.mendelu.pef.compose.petstore.architecture.CommunicationError
import cz.mendelu.pef.compose.petstore.architecture.CommunicationResult
import cz.mendelu.pef.compose.petstore.models.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class PetsRemoteRepositoryImpl(private val petsAPI: PetsAPI) : IPetsRemoteRepository {
    override suspend fun pets(status: String): CommunicationResult<List<Pet>> {
        try {
            val response = withContext(Dispatchers.IO){
                petsAPI.pets(status)
            }
            if (response.isSuccessful){
                if (response.body() != null){
                    return CommunicationResult.Success(response.body()!!)
                }else{
                    return CommunicationResult.Error(
                        CommunicationError(response.code(), response.errorBody().toString()))
                }

            }else{
                return CommunicationResult.Error(
                    CommunicationError(response.code(), response.errorBody().toString()))
            }

        }catch (e: Exception){
            return CommunicationResult.Exception(e)
        }
    }

    override suspend fun pet(id: Long): CommunicationResult<Pet> {
        return try {
            processResponse(withContext(Dispatchers.IO){petsAPI.pet(id)})
            
        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }
    }

    override suspend fun deletePet(id: Long): CommunicationResult<ResponseBody> {
        return try {
            processResponse(withContext(Dispatchers.IO){petsAPI.deletePet(id)})

        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }
    }


}