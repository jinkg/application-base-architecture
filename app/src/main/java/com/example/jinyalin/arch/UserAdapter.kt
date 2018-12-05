package com.example.jinyalin.arch

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.data.model.Result

/**
 * Yalin on 2018/12/5
 */
class UserAdapter(private val requestManager: RequestManager) :
    ListAdapter<Result, RecyclerView.ViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {
        return UserViewHolder.create(parent, requestManager)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userItem = getItem(position)
        if (userItem != null) {
            (holder as UserViewHolder).bind(userItem)
        }
    }


    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.phone == newItem.phone
            }

        }
    }
}