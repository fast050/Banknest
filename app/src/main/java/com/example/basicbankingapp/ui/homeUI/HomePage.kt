package com.example.basicbankingapp.ui.homeUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.basicbankingapp.R


@Composable
fun HomePage(title: String = stringResource(id = R.string.welcome_to_bank), onclick: () -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()){
            val (image, text1 , visaCardBanner , testimoniesList , button , text2) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.background_rec_shape_color),
                contentDescription = null,
                modifier = Modifier.constrainAs(image){
                    bottom.linkTo(parent.bottom)
                }
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .constrainAs(text1) {
                        top.linkTo(parent.top)
                    }
                    .padding(top = 16.dp, start = 16.dp)
            )
            VisaCardBanner(modifier = Modifier.constrainAs(visaCardBanner){
                top.linkTo(text1.bottom)
            })
            Text(
                text = stringResource(id = R.string.bank_customers),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .constrainAs(text2) {
                        top.linkTo(visaCardBanner.bottom)
                    }
                    .padding(16.dp)
            )
            TestimoniesList(modifier = Modifier.constrainAs(testimoniesList){
                top.linkTo(text2.bottom)
            })
            Button(
                onClick = { onclick() }, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(button) {
                        top.linkTo(testimoniesList.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.show_all_customer_btn),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }

  }

@Composable
fun VisaCardBanner(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_card_visa),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .wrapContentWidth(unbounded = true, align = Alignment.Start)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_card_vias_v2),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .wrapContentWidth(unbounded = true, align = Alignment.Start)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewHomePage() {
    ComposeBanknessAppTheme {
        HomePage{}
    }
}
