package ch.anoop.rickmorty.network.retrofit

import retrofit2.Call
import retrofit2.http.*

interface RickMortyAPI {

    @Headers("Content-Type: application/json")
    @POST("graphql")
    fun getAllDetails(@Body requestBody: String): Call<String>

}
