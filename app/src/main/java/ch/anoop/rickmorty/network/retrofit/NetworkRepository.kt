package ch.anoop.rickmorty.network.retrofit

import ch.anoop.rickmorty.network.response_model.GetAllResponseModel
import retrofit2.Callback

object NetworkRepository {

    fun fetchAllData(requestBody:String, callback: Callback<String>) {
        RetrofitClient.rickMortyAPI.getAllDetails(requestBody).enqueue(callback)
    }

}