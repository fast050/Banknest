package com.example.basicbankingapp.data

import com.example.basicbankingapp.R
import com.example.basicbankingapp.model.Testimony
import com.example.basicbankingapp.model.User


object DataProvider {


   private val user1 = User(
        0, "Bernard Diana",
        R.drawable.male_1, "+971 55 369 6694",
        "BernadDinard@gmail.com", 5000.50
    )

   private val user2 = User(
        1, "Tessa Verity",
        R.drawable.female_1, "+971 66 254 6584",
        "TessaVerity@gmail.com", 2500.50
    )

  private  val user3 = User(
        2, "Hellen Jaouad",
        R.drawable.female_2, "+971 25 151 2454",
        "HellenJaouad@gmail.com", 6000.00
    )

  private  val user4 = User(
        3, "Jaxson Jezza",
        R.drawable.male_2, "+971 33 484 3698",
        "JaxsonJezza@gmail.com", 1000.50
    )

  private  val user5 = User(
        4, "Khalid Elfaki",
        R.drawable.male_6, "+971 55 369 1478",
        "KhalidElfaki@gmail.com", 3000.00
    )

   private val user6 = User(
        5, "John Parker",
        R.drawable.male_4, "+971 55 369 6694",
        "BernadDinard@gmail.com", 5000.50
    )

   private val user7 = User(
        6, "Haywood Cortney",
        R.drawable.female_3, "+971 55 369 6694",
        "HaywoodCortney@gmail.com", 4000.50
    )

  private  val user8 = User(
        7, "Caden Jimmy",
        R.drawable.female_4, "+971 54 151 6551",
        "CadenJimmy@gmail.com", 900.50
    )

  private  val user9 = User(
        8, "Eddy Jon",
        R.drawable.male_5, "+971 41 251 1515",
        "BernadDinard@gmail.com", 100.50
    )

   private val user10 = User(
        9, "Eiadya Bill",
        R.drawable.female_5, "+971 33 152 555",
        "EiadyaBill@gmail.com", 2000.50
    )


    val listOfCostumer = listOf(
        user1, user2, user3, user4, user5, user6, user7, user8, user9, user10
    )


    private val Testimony1 = Testimony(
        userNameRes = R.string.bank_name_user1 ,
        userImageRes = R.drawable.male_6,
        userCommentRes = R.string.bank_feedback_user1
    )

    private val Testimony2 = Testimony(
        userNameRes = R.string.bank_name_user2 ,
        userImageRes = R.drawable.male_2,
        userCommentRes = R.string.bank_feedback_user2
    )

    private val Testimony3 = Testimony(
        userNameRes = R.string.bank_name_user3 ,
        userImageRes = R.drawable.male_3,
        userCommentRes = R.string.bank_feedback_user3
    )

    val listOfComment = listOf(
      Testimony1 , Testimony2 , Testimony3
    )

}
