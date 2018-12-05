package com.example.data

import com.example.data.database.ArchDatabase
import com.example.data.model.Demo
import com.example.data.model.Users
import com.example.data.network.NetworkApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Yalin on 2018/11/29
 */
class ArchRepository(
    private var networkApi: NetworkApi,
    private var archDatabase: ArchDatabase
) {

    fun getUsers(): Observable<Users> {
        return networkApi.getRandomUsers(10)
    }

    fun getDemosDb(): Observable<List<Demo>> {
        return Observable.create { emitter ->
            emitter.onNext(archDatabase.userDao().getDemos())
            emitter.onComplete()
        }
    }
}
