package com.mendelu.xstast12.homework2.communication


import com.mendelu.xstast12.homework2.architecture.CommunicationResult
import com.mendelu.xstast12.homework2.model.Coordinate
import com.mendelu.xstast12.homework2.model.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MockRemoteRepositoryImpl(private val mockAPI: MockAPI):IMockRemoteRepository {
    override suspend fun getStores(): CommunicationResult<List<Store>> {
        return try {
            processResponse(withContext(Dispatchers.IO){mockAPI.getStores()})

        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }
    }

    override suspend fun getBrnoBoundaries(): CommunicationResult<List<Coordinate>> {
        return try {
            processResponse(withContext(Dispatchers.IO){mockAPI.getBrnoBoundaries()})

        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }
    }

}