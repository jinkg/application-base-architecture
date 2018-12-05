package com.example.data

import android.annotation.SuppressLint
import android.arch.paging.PageKeyedDataSource
import com.example.data.model.Result
import com.example.data.network.NetworkApi
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * Yalin on 2018/12/5
 */
class PageKeyedUsersDataSource(private var networkApi: NetworkApi) : PageKeyedDataSource<Int, Result>() {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    private var currentPage = 0

    val networkState: PublishSubject<NetworkState> = PublishSubject.create()
    val initialLoad: PublishSubject<NetworkState> = PublishSubject.create()

    fun retryAllFailed() {

    }


    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        networkState.onNext(NetworkState.LOADING)
        initialLoad.onNext(NetworkState.LOADING)
        currentPage = 0
        networkApi.getRandomUsers(NETWORK_PAGE_SIZE)
            .subscribe(
                {
                    callback.onResult(it.getResults(), 0, 1)
                    networkState.onNext(NetworkState.LOADED)
                    initialLoad.onNext(NetworkState.LOADED)
                }, {
                    Timber.e(it)
                    networkState.onNext(NetworkState.error(it.message))
                    initialLoad.onNext(NetworkState.error(it.message))
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Result>
    ) {
        networkState.onNext(NetworkState.LOADING)
        networkApi.getRandomUsers(NETWORK_PAGE_SIZE)
            .subscribe(
                {
                    callback.onResult(it.getResults(), currentPage++)
                    networkState.onNext(NetworkState.LOADED)
                }, {
                    Timber.e(it)
                    networkState.onNext(NetworkState.error(it.message))
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