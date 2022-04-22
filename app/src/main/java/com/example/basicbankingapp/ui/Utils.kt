package com.example.basicbankingapp.ui

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.basicbankingapp.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun formatDate():String
{
    val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
    val calendar = Calendar.getInstance()

    return formatter.format(calendar.time)
}

fun Double.formatMoneyAmount(): String = NumberFormat.getCurrencyInstance().format(this)


//Draw transaction text with proper color
fun formatSendReceiveColor( context:Context,transactionType: String): Int {
    return if (transactionType == TransactionType.SendMoney.name)
    {
        ContextCompat.getColor(context, R.color.send_money)
    }else
        ContextCompat.getColor(context,R.color.receive_money)
}

// this will add + or - accord to the send or receive type example: + $100 , - $100
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

//    java.lang.NumberFormatException: For input string: "5,5555"
fun String.trimNumber():String {

    Log.d("TAG", "toNormalNumber: ${this.length}")

    if (this.length < 3)
        return ""

     if (this.length in 3..8) {
         return this.substring(1, this.length - 3)
     }

     if (this.length == 9) {
         return this[1] + this.substring(3, length - 3)
     }

    if (this.length == 10) {
        return this[1] +""+this[2] + this.substring(4, length - 3)
    }

        return "0"

}

fun Int?.toCurrency():String{
  return  NumberFormat.getCurrencyInstance().format(this)
}

fun String.trimLastNumber():String
{
  return  this.subSequence(0, this.length - 1).toString()
}