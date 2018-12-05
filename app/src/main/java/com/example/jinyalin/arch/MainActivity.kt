package com.example.jinyalin.arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.inject.ActivityModule
import com.example.jinyalin.arch.inject.activity.DaggerActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: UserAdapter

    private lateinit var viewModel: ArchViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerActivityComponent
            .builder()
            .appComponent(App.getApp(this).getAppComponent())
            .activityModule(ActivityModule(this))
            .build()
            .injectMainActivity(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ArchViewModel::class.java)

        initRecyclerView()

        populateUsers()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.user_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
    }

    private fun populateUsers() {
        disposable.add(
            viewModel.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Timber.d(it.getResults().size.toString())
                        adapter.submitList(it.getResults())
                    },
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
