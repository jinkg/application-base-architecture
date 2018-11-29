package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.Demo
import java.util.concurrent.Executors

/**
 * Yalin on 2018/11/29
 */
@Database(entities = [Demo::class], version = 1)
abstract class ArchDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: ArchDatabase? = null

        fun getInstance(context: Context): ArchDatabase {
            if (INSTANCE == null) {
                synchronized(ArchDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<ArchDatabase>(
                            context.applicationContext,
                            ArchDatabase::class.java, "Arch.db"
                        ).addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadScheduledExecutor().execute {
                                    getInstance(context).userDao().insertDemo(Demo(value = "Hello"))
                                    getInstance(context).userDao().insertDemo(Demo(value = "World"))
                                }
                            }
                        }).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun userDao(): UserDao
}
