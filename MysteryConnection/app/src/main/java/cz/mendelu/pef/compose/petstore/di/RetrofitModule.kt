package cz.mendelu.pef.compose.petstore.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import cz.mendelu.pef.compose.petstore.models.Data
import cz.mendelu.pef.compose.petstore.models.Item
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

val retrofitModule = module {
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
        .apply { registerTypeAdapter(Data::class.java, DataDeserializer()) }
        .create()

    return Retrofit.Builder().baseUrl("https://630f1220498924524a855007.mockapi.io/test/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

class DataDeserializer : JsonDeserializer<Data>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Data {
        val jsonObject = json?.asJsonObject?.getAsJsonObject("regular")

        if(jsonObject != null){
            val data: MutableList<Item> = mutableListOf()
            for (entry in jsonObject.entrySet()){
                try {
                    data.add(Item(entry.key,entry.value.asDouble))
                }catch (exception: java.lang.Exception){
                    exception.printStackTrace()
                }
            }

            val dataObject = Data()
            dataObject.data = data
            return dataObject
        }
        return Data()
    }

}

