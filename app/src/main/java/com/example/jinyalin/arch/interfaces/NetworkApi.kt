package com.example.jinyalin.arch.interfaces

import com.example.jinyalin.arch.model.RandomUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Yalin on 2018/11/29
 */
interface NetworkApi {

    @GET("api")
    fun getRandomUsers(@Query("results") size: Int): Call<RandomUsers>

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }
}
