package com.mendelu.xstast12.homework2.communication

import com.mendelu.xstast12.homework2.architecture.CommunicationResult
import com.mendelu.xstast12.homework2.architecture.IBaseRemoteRepository
import com.mendelu.xstast12.homework2.model.Coordinate
import com.mendelu.xstast12.homework2.model.Coordinates
import com.mendelu.xstast12.homework2.model.Store

interface IMockRemoteRepository: IBaseRemoteRepository {
    suspend fun getStores(): CommunicationResult<List<Store>>
    suspend fun getBrnoBoundaries(): CommunicationResult<Coordinates>
}