package com.example.basicbankingapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.basicbankingapp.data.UserDao
import com.example.basicbankingapp.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserDatabase(
        @ApplicationContext context: Context,
        callback:UserDatabase.UserDatabaseCallBack
    ): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, UserDatabase.DATABASE_NAME)
            .addCallback(callback)
            .build()
    }


    //no need to add singleton cause dao is singleton by RoomDatabase
    @Provides
    fun providerUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }


    @Singleton
    @ApplicationScope
    @Provides
    fun providerCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
}

@Qualifier
annotation class ApplicationScope