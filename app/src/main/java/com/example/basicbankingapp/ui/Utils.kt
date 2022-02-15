package com.example.basicbankingapp.ui

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.basicbankingapp.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate():String
{
    val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
    val calendar = Calendar.getInstance()

    return formatter.format(calendar.time)
}

fun Double.formatMoneyAmount(): String = NumberFormat.getCurrencyInstance().format(this)

fun formatSendReceiveColor(transactionType : String,context: Context): Int {
    return if (transactionType == TransactionType.SendMoney.name)
    {
        ContextCompat.getColor(context, R.color.send_money)
    }else
        ContextCompat.getColor(context,R.color.receive_money)
}

// this will add + or - accord to the send or receive type exp: + $100 , - $100
fun String.formatMoneyTransaction(transactionType: String): String {
    return if(transactionType == TransactionType.SendMoney.name )
    {
        "- $this"
    }else
        "+ $this"

}

fun isValidAmount(userCurrentAmount:Double,userInputAmount:Double):Boolean
{
    return userCurrentAmount>userInputAmount
}
