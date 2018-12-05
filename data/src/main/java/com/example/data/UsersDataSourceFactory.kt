package com.example.data

import android.arch.paging.DataSource
import com.example.data.model.Result
import com.example.data.network.NetworkApi

/**
 * Yalin on 2018/12/5
 */
class UsersDataSourceFactory(private val networkApi: NetworkApi) : DataSource.Factory<Int, Result>() {
    override fun create(): DataSource<Int, Result> {
        return PageKeyedUsersDataSource(networkApi)
    }

}