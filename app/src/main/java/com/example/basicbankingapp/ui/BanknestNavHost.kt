package com.example.basicbankingapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.ui.homeUI.HomePage
import com.example.basicbankingapp.ui.listCustomerUI.BankCustomersList
import kotlinx.serialization.Serializable

@Composable
fun BanknestNavHost(
    navHostController: NavHostController,
    shardViewModel: UsersViewModel ,
    onNavigateToCustomerDetails : (User) -> Unit = {}
) {

    NavHost(
        navController = navHostController,
        startDestination = HomePage
    ) {

        composable<HomePage> {
            HomePage {
                navHostController.navigate(ListCustomer)
            }
        }

        composable<ListCustomer> {
            BankCustomersList(shareViewModel =shardViewModel) {
               onNavigateToCustomerDetails(it)
            }
        }
    }

}


@Serializable
object HomePage

@Serializable
object ListCustomer
