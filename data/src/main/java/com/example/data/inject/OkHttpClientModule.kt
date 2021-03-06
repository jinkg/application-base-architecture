package com.example.data.inject

import android.content.Context
import com.example.data.inject.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

import java.io.File

/**
 * Yalin on 2018/11/29
 */
@Module(includes = [ContextModule::class])
class OkHttpClientModule {
    @Provides
    @ApplicationScope
    fun getOkHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun getCache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1000 * 1000).toLong())
    }

    @Provides
    fun getFile(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")

        file.mkdirs()
        return file
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor

    }
}
