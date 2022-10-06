package cz.mendelu.pef.compose.petstore.communication

import cz.mendelu.pef.compose.petstore.models.Data
import cz.mendelu.pef.compose.petstore.models.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

// TODO dodělat
interface API {

    @Headers("Content-Type: application/json")
    @GET(".")
    suspend fun getItems(): Response<Data>

}