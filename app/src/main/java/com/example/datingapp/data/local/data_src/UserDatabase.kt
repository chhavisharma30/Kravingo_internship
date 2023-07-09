package com.example.datingapp.data.local.data_src

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room


@Database(entities = [Match::class, Message::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getMatchDAO() : MatchDAO
    abstract fun getMessageDAO() : MessageDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDataBase(context: Context): UserDatabase {
            val dbInstance = INSTANCE

            if(dbInstance != null) {
                return dbInstance
            }
            synchronized(this) { // TODO: Check later with changing the context
                val newDbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = newDbInstance
                return newDbInstance
            }
        }
    }



}