package cz.mendelu.pef.compose.petstore.communication

import cz.mendelu.pef.compose.petstore.architecture.CommunicationResult
import cz.mendelu.pef.compose.petstore.models.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException

// TODO dodělat
class RemoteRepositoryImpl(private val api: API) : IRemoteRepository {
    override suspend fun getItems(): CommunicationResult<Data> {
        return try {
            processResponse(withContext(Dispatchers.IO){api.getItems()})

        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }
    }
}