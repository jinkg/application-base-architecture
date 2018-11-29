package com.example.data.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Yalin on 2018/11/29
 */
@Entity(tableName = "users")
data class Users(
    @SerializedName("results")
    @Expose
    private var results: List<Result>
) {
    fun getResults() = results

    fun setResults(results: List<Result>) {
        this.results = results
    }
}
