package com.example.jinyalin.arch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.RequestManager
import com.example.data.model.Result

/**
 * Yalin on 2018/12/5
 */
class UserViewHolder(view: View, private val requestManager: RequestManager) : RecyclerView.ViewHolder(view) {
    @BindView(R.id.image)
    lateinit var image: ImageView
    @BindView(R.id.name)
    lateinit var name: TextView

    init {
        ButterKnife.bind(this, view)
    }

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