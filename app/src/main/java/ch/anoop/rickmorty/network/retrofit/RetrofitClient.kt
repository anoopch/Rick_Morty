package ch.anoop.rickmorty.network.retrofit

import ch.anoop.rickmorty.BuildConfig.BUILD_TYPE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val RICK_MORTY_GRAPHQL_API_ENDPOINT = "https://rickandmortyapi.com/"

    private val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level = if (BUILD_TYPE.contentEquals("debug")) BODY else NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        Retrofit.Builder()
            .baseUrl(RICK_MORTY_GRAPHQL_API_ENDPOINT)
            .client(OkHttpClient.Builder().addInterceptor(logging).build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val rickMortyAPI: RickMortyAPI by lazy {
        retrofitClient
            .build()
            .create(RickMortyAPI::class.java)
    }
}