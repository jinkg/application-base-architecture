package com.example.data.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_name")
data class Name(
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("first")
    @Expose
    var first: String,
    @SerializedName("last")
    @Expose
    var last: String
)
