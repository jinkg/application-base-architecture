package com.example.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.apache.commons.lang3.builder.ToStringBuilder


data class Id(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("value")
    @Expose
    var value: String? = null
)
