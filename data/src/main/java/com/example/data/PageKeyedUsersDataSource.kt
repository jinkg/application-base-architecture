package com.example.data

import android.annotation.SuppressLint
import android.arch.paging.PageKeyedDataSource
import com.example.data.model.Result
import com.example.data.network.NetworkApi
import timber.log.Timber

/**
 * Yalin on 2018/12/5
 */
class PageKeyedUsersDataSource(private var networkApi: NetworkApi) : PageKeyedDataSource<Int, Result>() {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    private var currentPage = 0

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        currentPage = 0
        networkApi.getRandomUsers(NETWORK_PAGE_SIZE)
            .subscribe(
                {
                    callback.onResult(it.getResults(), 0, 1)
                }, {
                    Timber.e(it)
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Result>
    ) {
        networkApi.getRandomUsers(NETWORK_PAGE_SIZE)
            .subscribe(
                {
                    callback.onResult(it.getResults(), currentPage++)
                }, {
                    Timber.e(it)
                }
            )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Result>
    ) {
        // ignored, since we only ever append to our initial load
    }

}