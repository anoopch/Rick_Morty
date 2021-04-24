package ch.anoop.rickmorty.repository.network

import android.os.Looper
import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class RickMortyAPI {

    private val apiURL = "https://rickandmortyapi.com/graphql"

    fun getAPIClient(): ApolloClient {

        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Accessing API client from Non-Main thread is not allowed"
        }

        return ApolloClient.builder()
            .serverUrl(apiURL)
            .okHttpClient(OkHttpClient.Builder().build())
            .build()
    }
}