package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Yalin on 2018/11/29
 */
@Entity(tableName = "demo")
data class Demo(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var value: String? = null
)
