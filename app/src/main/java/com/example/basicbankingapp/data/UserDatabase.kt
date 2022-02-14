package com.example.basicbankingapp.data

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import java.util.concurrent.Executors


@Database(entities = [User::class, Transfers::class], version = 1)
abstract class UserDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao


    companion object {


        private const val DATABASE_NAME = "User Database"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        // For Singleton instantiation
        @Volatile private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): UserDatabase {
            return Room.databaseBuilder(context, UserDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //pre-populate data
                        Executors.newSingleThreadExecutor().execute {
                            instance?.let {
                                it.userDao().insertUsers(DataProvider.listOfCostumer)
                            }
                        }
                    }
                })
                .build()

            }


        }

    }
