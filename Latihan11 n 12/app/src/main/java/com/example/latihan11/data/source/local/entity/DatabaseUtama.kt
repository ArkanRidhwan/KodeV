package com.example.latihan11.data.source.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GameEntity::class, KartunEntity::class], version = 1, exportSchema = false)
abstract class DatabaseUtama : RoomDatabase() {

    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseUtama? = null

        fun getInstance(context: Context): DatabaseUtama =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseUtama::class.java,
                    "Database.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}

/*
companion object{
    @Volatile
    private var INSTANCE: GameDatabase? = null

    fun getInstance(context: Context): GameDatabase =
        INSTANCE ?: synchronized(this){
            Room.databaseBuilder(context.applicationContext,
                GameDatabase::class.java,
                "Games.db"
            ).build().apply {
                INSTANCE = this
            }
        }
}*/
