package com.example.jinyalin.arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.data.ArchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var archRepository: ArchRepository

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.getApp(this).getAppComponent().injectMainActivity(this)

        populateUsers()
    }

    private fun populateUsers() {
        disposable.add(
            archRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users -> Timber.d(users.getResults().size.toString()) },
                    { throwable -> Timber.e(throwable) })
        )

        disposable.add(
            archRepository.getDemosDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { demos ->
                        Timber.d(demos.size.toString())
                    },
                    { throwable -> Timber.e(throwable) })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
