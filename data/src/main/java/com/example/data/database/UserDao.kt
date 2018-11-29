package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
