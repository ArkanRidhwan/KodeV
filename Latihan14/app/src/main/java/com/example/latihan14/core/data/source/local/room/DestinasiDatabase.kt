package com.example.latihan14.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DestinasiEntity::class], version = 1, exportSchema = false)
abstract class DestinasiDatabase : RoomDatabase() {


    companion object {

        @Volatile
        private var INSTANCE: DestinasiDatabase? = null

        fun getInstance(context: Context): DestinasiDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DestinasiDatabase::class.java,
                    "Destinasi.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}