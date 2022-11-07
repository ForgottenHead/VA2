package com.mendelu.xstast12.homework2.communication

import com.mendelu.xstast12.homework2.model.Coordinate
import com.mendelu.xstast12.homework2.model.Coordinates
import com.mendelu.xstast12.homework2.model.Store
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MockAPI {
    @Headers("Content-Type: application/json")
    @GET("stores")
    suspend fun getStores(): Response<List<Store>>

    @Headers("Content-Type: application/json")
    @GET("brno")
    suspend fun getBrnoBoundaries(): Response<Coordinates>
}