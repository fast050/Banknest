package com.example.basicbankingapp.ui.listCustomerUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.basicbankingapp.R
import com.example.basicbankingapp.data.DataProvider
import com.example.basicbankingapp.model.User
import com.example.basicbankingapp.ui.formatMoneyAmount
import com.example.basicbankingapp.ui.Theme.ComposeBanknessAppTheme
import com.example.basicbankingapp.ui.Theme.getGray
import com.example.basicbankingapp.ui.UsersViewModel

@Composable
fun BankCustomer(modifier: Modifier = Modifier, customer: User, onClick: (User) -> Unit) {
    Card(
        modifier = modifier
            .clickable {
                onClick(customer)
            },
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row {
                Image(
                    painter = painterResource(id = customer.userProfilePicture),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(50.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = customer.userName,
                    modifier = Modifier.padding(15.dp)
                )
            }

            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(
                    modifier = modifier
                        .weight(1f)
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.email_label),
                        style = MaterialTheme.typography.bodySmall,
                        color = getGray(false)
                    )
                    Text(
                        text = customer.userEmail,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Column {
                    Text(
                        text = stringResource(id = R.string.total_balance_label),
                        style = MaterialTheme.typography.bodySmall,
                        color = getGray(false)
                    )
                    Text(
                        text = customer.userCurrentBalance.formatMoneyAmount(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

//stateful : use from fragment or ui
@Composable
fun BankCustomersList(
    modifier: Modifier = Modifier,
    shareViewModel: UsersViewModel,
    onClick: (User) -> Unit
) {
    val customers by shareViewModel.users.observeAsState()
    customers?.let { list->
        BankCustomerList(list, onClick)
    }
}

// stateless : use for Preview , reusable
@Composable
private fun BankCustomerList(
    list: List<User>,
    onClick: (User) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) {
            BankCustomer(customer = it, onClick = onClick)
        }
    }
}


@Preview
@Composable
private fun PreviewBankCustomer() {
    ComposeBanknessAppTheme {
        BankCustomerList(list = DataProvider.listOfCostumer) {
        }
    }
}