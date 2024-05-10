package com.example.basicbankingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.model.UserWithTransfers

@Dao
interface UserDao {



    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(users: User)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(users: List<User>)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun removeUser(user: User)

    @Transaction
    @Query("select * from user")
    fun getAllUser():LiveData<List<User>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransfers(transfers: Transfers)

    @Update
    suspend fun updateTransfers(transfers: Transfers)

    @Delete
    suspend fun removeTransfers(transfers: Transfers)

    @Transaction
    @Query("select * from Transfers")
    suspend fun getAllTransfers(): List<Transfers>

    @Transaction
    @Query("select * from  User where userId= :userId ")
    fun getAllUserTransfers(userId:Int): LiveData<UserWithTransfers>

}