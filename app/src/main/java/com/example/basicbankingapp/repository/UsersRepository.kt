package com.example.basicbankingapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basicbankingapp.data.UserDao
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersRepository @Inject constructor(private val userDao : UserDao) {

       // fun listTransaction(userId: Int) = userDao.getAllUserTransfers(userId = userId)

    fun getAllUsers() = userDao.getAllUser()

    fun getListTransaction(userId: Int) = userDao.getAllUserTransfers(userId = userId)

    suspend fun insertTransaction(transaction: Transfers) {
        userDao.insertTransfers(transaction)
    }

   suspend fun updateAmountAfterTransaction(
        userTransferTo: User,
        userTransferFrom: User,
        transferAmount: Double
    ) {
        val sender =
            userTransferFrom.copy(userCurrentBalance = userTransferFrom.userCurrentBalance - transferAmount)
        val receiver =
            userTransferTo.copy(userCurrentBalance = userTransferTo.userCurrentBalance + transferAmount)
        userDao.updateUser(receiver)
        userDao.updateUser(sender)
    }

}