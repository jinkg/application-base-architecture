package com.example.data.inject

import com.example.data.inject.scope.ApplicationScope
import com.example.data.network.NetworkApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Yalin on 2018/11/29
 */
@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun getGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Provides
    @ApplicationScope
    fun getNetworkApi(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun getRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
        rxJavaCallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkApi.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJavaCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @ApplicationScope
    fun getGsonConvertFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @ApplicationScope
    fun getRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}
