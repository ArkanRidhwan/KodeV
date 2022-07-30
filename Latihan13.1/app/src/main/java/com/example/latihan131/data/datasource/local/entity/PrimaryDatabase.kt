package com.example.latihan131.data.datasource.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [KartunEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PrimaryDatabase: RoomDatabase() {
    abstract fun dao(): DAO

    companion object{
        @Volatile
        private var INSTANCE: PrimaryDatabase? = null

        fun getInstance(context: Context): PrimaryDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    PrimaryDatabase::class.java,
                    "Database.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}