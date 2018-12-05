package com.example.jinyalin.arch

import android.arch.lifecycle.ViewModel
import com.example.data.ArchRepository

/**
 * Yalin on 2018/12/5
 */
class ArchViewModel(private val archRepository: ArchRepository) : ViewModel() {
    fun getUsers() = archRepository.getUsers()

    fun getDemosDb() = archRepository.getDemosDb()
}