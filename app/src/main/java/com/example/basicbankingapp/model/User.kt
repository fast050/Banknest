package com.example.basicbankingapp.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = false)
    val userId: Int,
    val userName: String,
    @DrawableRes val userProfilePicture:Int,
    val userMobileNumber: String,
    val userEmail: String,
    val userCurrentBalance: Double,
):Parcelable
