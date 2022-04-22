package com.example.basicbankingapp.ui

import androidx.lifecycle.*

import com.example.basicbankingapp.data.UserDao
import com.example.basicbankingapp.model.Transfers
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

enum class TransactionType {
    SendMoney, ReceiveMoney
}

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: UsersRepository) : ViewModel() {

    val users = repository.getAllUsers()

    val recyclerUserIdToHide: Int get() = _recyclerUserIdToHide
    private var _recyclerUserIdToHide = 0

    fun setRecyclerUserIdToHide(userId: Int) {
        _recyclerUserIdToHide = userId
    }

    fun listTransaction(userId: Int) = repository.getListTransaction(userId)

    fun insertTransaction(transaction: Transfers) = viewModelScope.launch {
        repository.insertTransaction(transaction)
    }

    fun updateAmountAfterTransaction(
        userTransferTo: User,
        userTransferFrom: User,
        transferAmount: Double
    ) = viewModelScope.launch {

        repository.updateAmountAfterTransaction(
            userTransferTo = userTransferTo,
            userTransferFrom = userTransferFrom,
            transferAmount = transferAmount
        )
    }

    private var _userSelected = MutableLiveData<User?>(null)
    val userSelected: LiveData<User?> get() = _userSelected

    fun setUserSelected(user: User?) {
        _userSelected.value = user
    }
}