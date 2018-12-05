package com.example.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.data.model.Demo

/**
 * Yalin on 2018/11/29
 */
@Dao
interface UserDao {
    @Query("SELECT * from demo")
    fun getDemos(): List<Demo>

    @Insert
    fun insertDemo(demo: Demo)
}
