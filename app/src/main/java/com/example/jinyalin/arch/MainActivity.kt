package com.example.jinyalin.arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jinyalin.arch.interfaces.NetworkApi
import com.example.jinyalin.arch.model.RandomUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkApi: NetworkApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.getApp(this).getAppComponent().injectMainActivity(this)

        populateUsers()
    }

    private fun populateUsers() {
        networkApi.getRandomUsers(10).enqueue(object : Callback<RandomUsers> {
            override fun onFailure(call: Call<RandomUsers>, t: Throwable) {
            }

            override fun onResponse(call: Call<RandomUsers>, response: Response<RandomUsers>) {
                Timber.d(response.body()!!.results.size.toString())
            }
        })
    }
}
