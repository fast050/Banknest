package com.example.basicbankingapp.ui

import androidx.lifecycle.*

import com.example.basicbankingapp.data.UserDao
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

enum class TransactionType {
    SendMoney, ReceiveMoney
}

class UsersViewModel(private val userDao: UserDao) : ViewModel() {

    val users = userDao.getAllUser()

    val recyclerUserIdToHide :Int get() = _recyclerUserIdToHide
     private var _recyclerUserIdToHide = 0
      fun setRecyclerUserIdToHide( userId: Int){
            _recyclerUserIdToHide=userId
        }

    fun listTransaction(userId: Int) = userDao.getAllUserTransfers(userId = userId)

    fun insertTransaction(transaction: Transfers) = viewModelScope.launch {
        userDao.insertTransfers(transaction)
    }

    fun updateAmountAfterTransaction(
        userTransferTo: User,
        userTransferFrom: User,
        transferAmount: Double
    ) = viewModelScope.launch {
        val sender =
            userTransferFrom.copy(userCurrentBalance = userTransferFrom.userCurrentBalance - transferAmount)
        val receiver =
            userTransferTo.copy(userCurrentBalance = userTransferTo.userCurrentBalance + transferAmount)
        userDao.updateUser(receiver)
        userDao.updateUser(sender)
    }

    private var _userSelected = MutableLiveData<User?>(null)
    val userSelected: LiveData<User?> get() = _userSelected

    fun setUserSelected(user: User?) {
        _userSelected.value = user
    }




    class UserViewModelFactory(private val dao: UserDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
                return UsersViewModel(dao) as T
            } else
                throw IllegalArgumentException("UnKnown ViewModel class")
        }
    }
}