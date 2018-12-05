package com.example.jinyalin.arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ArchViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.getApp(this).getAppComponent().injectMainActivity(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ArchViewModel::class.java)

        populateUsers()
    }

    private fun populateUsers() {
        disposable.add(
            viewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users -> Timber.d(users.getResults().size.toString()) },
                    Timber::e
                )
        )

        disposable.add(
            viewModel.getDemosDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { demos ->
                        Timber.d(demos.size.toString())
                    },
                    Timber::e
                )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
