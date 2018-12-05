package com.example.data.model


import android.arch.persistence.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_result")
data class Result(
    @SerializedName("gender")
    @Expose
    var gender: String,
    @SerializedName("name")
    @Expose
    var name: Name?,
    @SerializedName("email")
    @Expose
    var email: String,
    @SerializedName("phone")
    @Expose
    var phone: String,
    @SerializedName("cell")
    @Expose
    var cell: String,
    @SerializedName("nat")
    @Expose
    var nat: String?,
    @SerializedName("id")
    @Expose
    var id: Id? = null,
    @SerializedName("picture")
    @Expose
    var picture: Picture? = null
)
