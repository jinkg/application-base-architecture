package com.example.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Yalin on 2018/11/29
 */
@Entity(tableName = "demo")
data class Demo(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var value: String? = null
)
