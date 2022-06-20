package com.example.latihan121.data.source.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [GameEntity::class, KartunEntity::class, NasaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PrimaryDatabase : RoomDatabase() {
    abstract fun dao(): Dao

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