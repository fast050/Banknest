package com.example.basicbankingapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTransfers(
   @Embedded val user: User,
   @Relation(
       parentColumn = "userId",
       entityColumn = "userId"
   )
   val transfers:List<Transfers>
)