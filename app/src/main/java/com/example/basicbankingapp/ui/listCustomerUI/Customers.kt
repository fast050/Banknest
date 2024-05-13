package com.example.basicbankingapp.ui.listCustomerUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun BankCustomer(modifier: Modifier = Modifier, customer: User) {
    Card(modifier = modifier.padding(bottom = 20.dp)) {
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



@Preview
@Composable
private fun PreviewBankCustomer() {
    ComposeBanknessAppTheme {
        BankCustomer(customer = DataProvider.listOfCostumer[0])
    }
}