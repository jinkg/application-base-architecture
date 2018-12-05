package com.example.jinyalin.arch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.data.model.Result

/**
 * Yalin on 2018/12/5
 */
class UserViewHolder(view: View, private val requestManager: RequestManager) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.image)
    private val name: TextView = view.findViewById(R.id.name)

    fun bind(result: Result) {
        requestManager.load(result.picture?.large)
            .into(image)

        name.text = String.format(
            "%s %s", result.name?.first, result.name?.last
        )
    }

    companion object {
        fun create(parent: ViewGroup, requestManager: RequestManager): UserViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_user, parent, false)
            return UserViewHolder(view, requestManager)
        }
    }
}