package com.example.jinyalin.arch

import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.data.NetworkState
import com.example.data.inject.ActivityModule
import com.example.data.model.Demo
import com.example.data.model.Result
import com.example.jinyalin.arch.inject.activity.DaggerActivityComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @BindView(R.id.user_list)
    lateinit var recyclerView: RecyclerView

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

        ButterKnife.bind(this)
        initRecyclerView()

        populateUsers()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
    }

    private fun populateUsers() {
        disposable.add(
            viewModel.users
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::consumeUsers, Timber::e)
        )

        disposable.add(
            viewModel.networkState
                .subscribe(this::consumeNetworkState)
        )

        disposable.add(
            viewModel.getDemosDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::consumeDemos, Timber::e)
        )
    }

    private fun consumeUsers(users: PagedList<Result>) {
        Timber.d(users.size.toString())
        adapter.submitList(users)
    }

    private fun consumeNetworkState(networkState: NetworkState) {
        Timber.d("Network status : ${networkState.status.name}")
    }

    private fun consumeDemos(demos: List<Demo>) {
        Timber.d(demos.size.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
