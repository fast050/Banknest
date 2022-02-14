package com.example.basicbankingapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.basicbankingapp.ui.TransactionType

@Entity
data class Transfers(
    @PrimaryKey(autoGenerate = true)
    val transferId: Int=0,
    val transferDate:String,
    val transferFrom: String,  // name of the transfer sender
    val transferTo: String,    // name of the transfer receiver
    val transferAmount: Double,
    @DrawableRes
    val transferToProfile: Int,
    @DrawableRes
    val transferFromProfile: Int,
    val transactionType : String,
    val userId:Int // join id for the 1 to many relationship

)

