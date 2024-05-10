package com.example.basicbankingapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Testimony(
   @StringRes val userNameRes :Int,
   @DrawableRes val userImageRes :Int,
   @StringRes val userCommentRes :Int
)
