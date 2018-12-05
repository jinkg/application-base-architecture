package com.example.data

import android.arch.paging.RxPagedListBuilder
import com.example.data.database.ArchDatabase
import com.example.data.model.Demo
import com.example.data.model.Result
import com.example.data.network.NetworkApi
import io.reactivex.Observable

/**
 * Yalin on 2018/11/29
 */
class ArchRepository(
    private var networkApi: NetworkApi,
    private var archDatabase: ArchDatabase
) {

    fun getUsers(): Listing<Result> {
        val sourceFactory = UsersDataSourceFactory(networkApi)
        val rxPagedList = RxPagedListBuilder(sourceFactory, 10).buildObservable()
        val refreshState = sourceFactory.sourceSubject.flatMap {
            it.initialLoad
        }
        val networkState = sourceFactory.sourceSubject.flatMap {
            it.networkState
        }
        return Listing(pageList = rxPagedList,
            networkState = networkState,
            refreshState = refreshState,
            refresh = {
                sourceFactory.sourceSubject.subscribe {
                    it.invalidate()
                }
            },
            retry = {
                sourceFactory.sourceSubject.subscribe {
                    it.retryAllFailed()
                }
            }
        )
    }

    fun getDemosDb(): Observable<List<Demo>> {
        return Observable.create { emitter ->
            emitter.onNext(archDatabase.userDao().getDemos())
            emitter.onComplete()
        }
    }
}
