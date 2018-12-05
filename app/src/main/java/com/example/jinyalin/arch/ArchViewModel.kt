package com.example.jinyalin.arch

import android.arch.lifecycle.ViewModel
import com.example.data.ArchRepository

/**
 * Yalin on 2018/12/5
 */
class ArchViewModel(private val archRepository: ArchRepository) : ViewModel() {
    private val archResult = archRepository.getUsers()

    val users = archResult.pageList

    val networkState = archResult.networkState

    val refreshState = archResult.refreshState

    fun refresh() {
        archResult.refresh.invoke()
    }

    fun retry() {
        archResult.retry.invoke()
    }

    fun getDemosDb() = archRepository.getDemosDb()
}