package com.example.data

import android.arch.paging.PagedList
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

    fun getUsers(): Observable<PagedList<Result>> {
        return RxPagedListBuilder(UsersDataSourceFactory(networkApi), 10).buildObservable()
    }

    fun getDemosDb(): Observable<List<Demo>> {
        return Observable.create { emitter ->
            emitter.onNext(archDatabase.userDao().getDemos())
            emitter.onComplete()
        }
    }
}
