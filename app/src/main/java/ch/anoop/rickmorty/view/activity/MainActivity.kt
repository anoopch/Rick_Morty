package ch.anoop.rickmorty.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ch.anoop.rickmorty.R
import ch.anoop.rickmorty.network.retrofit.NetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<String> {
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startNetworkCall()
    }

    private fun startNetworkCall() {
        NetworkRepository.fetchAllData(
            "{\"query\":\"query GetALL {\n character {\n id\nname\nstatus\n}\n}\"}",
            this
        )
    }

    override fun onResponse(
        call: Call<String>,
        response: Response<String>
    ) {
        Log.e(TAG, "onResponse: " + response.body())
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        Log.e(TAG, "onFailure: ")
    }
}