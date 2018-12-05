package com.example.data

import android.arch.paging.DataSource
import com.example.data.model.Result
import com.example.data.network.NetworkApi
import io.reactivex.subjects.PublishSubject

/**
 * Yalin on 2018/12/5
 */
class UsersDataSourceFactory(private val networkApi: NetworkApi) : DataSource.Factory<Int, Result>() {
    val sourceSubject: PublishSubject<PageKeyedUsersDataSource> = PublishSubject.create()
    override fun create(): DataSource<Int, Result> {
        val source = PageKeyedUsersDataSource(networkApi)
        sourceSubject.onNext(source)
        return source
    }

}