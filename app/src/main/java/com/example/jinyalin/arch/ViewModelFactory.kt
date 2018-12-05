package com.example.jinyalin.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.ArchRepository

/**
 * Yalin on 2018/12/5
 */
class ViewModelFactory(private val repository: ArchRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}