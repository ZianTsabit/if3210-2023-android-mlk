package com.example.malika

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item:: class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao

    companion object {

        @Volatile
        private var instance : AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "malika-database"
        )
            .allowMainThreadQueries()
            .build()

    }
}