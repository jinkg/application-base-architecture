package com.example.data.network

import com.example.data.model.Users
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Yalin on 2018/11/29
 */
interface NetworkApi {

    @GET("api")
    fun getRandomUsers(@Query("results") size: Int): Observable<Users>

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }
}
