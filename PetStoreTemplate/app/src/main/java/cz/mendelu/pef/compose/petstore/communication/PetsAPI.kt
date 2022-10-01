package cz.mendelu.pef.compose.petstore.communication

import cz.mendelu.pef.compose.petstore.models.Pet
import retrofit2.Response
import retrofit2.http.*

interface PetsAPI {

    @Headers("Content-Type: application/json")
    @GET("pet/findByStatus")
    suspend fun pets(@Query("status") status: String): Response<List<Pet>>

    @Headers("Content-Type: application/json")
    @GET("pet/{id}")
    suspend fun pet(@Path("id") id: Long): Response<Pet>
}