package com.example.jinyalin.arch.inject

import com.example.jinyalin.arch.inject.scope.ApplicationScope
import com.example.jinyalin.arch.interfaces.NetworkApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Yalin on 2018/11/29
 */
@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {

    internal val gson: Gson
        @Provides
        @ApplicationScope
        get() {
            val builder = GsonBuilder()
            return builder.create()
        }

    @Provides
    @ApplicationScope
    internal fun getNetworkApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }

    @Provides
    @ApplicationScope
    internal fun getRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @ApplicationScope
    internal fun getGsonConvertFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}
