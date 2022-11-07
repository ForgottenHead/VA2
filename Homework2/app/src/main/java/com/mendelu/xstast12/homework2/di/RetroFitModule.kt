package com.mendelu.xstast12.homework2.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mendelu.xstast12.homework2.model.Brno
import com.mendelu.xstast12.homework2.model.Coordinate
import com.mendelu.xstast12.homework2.model.Coordinates
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

val retrofitModule = module{
    factory { provideInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}


fun provideInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    val dispatcher = Dispatcher()
    httpClient.dispatcher(dispatcher)
    return httpClient.addInterceptor(httpLoggingInterceptor).build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    val gson = GsonBuilder()
        .setLenient()
        .apply { registerTypeAdapter(Coordinates::class.java, DataDeserializer()) }
        .create()

    return Retrofit.Builder()
        .baseUrl("https://3ae576f5-cc8d-4894-8c1e-cd1dc7b71099.mock.pstmn.io/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}


class DataDeserializer : JsonDeserializer<Coordinates>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Coordinates {
        val jsonArray = json?.asJsonObject?.getAsJsonArray("coordinates")

        if(jsonArray != null){
            val data: MutableList<Coordinate> = mutableListOf()
            for (array in jsonArray){
                try {
                    data.add(
                        Coordinate(array.asJsonArray.get(0).asDouble,
                            array.asJsonArray.get(1).asDouble))

                }catch (exception: java.lang.Exception){
                    exception.printStackTrace()
                }
            }

            val dataObject = Coordinates()
            dataObject.allCoordinates = data
            return dataObject
        }
        return Coordinates()
    }

}