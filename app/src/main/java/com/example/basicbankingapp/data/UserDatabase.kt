package com.example.basicbankingapp.data

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.basicbankingapp.di.ApplicationScope
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [User::class, Transfers::class], version = 1)
abstract class UserDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao


    //provider it will lazy initiate coroutineScope until we call get on it
    //it will solve the problem of circle dependency of callback and RoomBuilder
    class UserDatabaseCallBack @Inject constructor(
        private val database: Provider<UserDatabase>,
        @ApplicationScope private val coroutineScope: CoroutineScope
    ) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            //pre-populate data

            val dao = database.get().userDao()

            coroutineScope.launch {
                dao.insertUsers(DataProvider.listOfCostumer)
            }

        }
    }


    companion object {
        const val DATABASE_NAME = "UserDatabase_db"
    }

}


