package com.example.basicbankingapp.ui.homeUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicbankingapp.R
import com.example.basicbankingapp.data.DataProvider
import com.example.basicbankingapp.model.Testimony

@Composable
fun TestimonyItem(
    testimony :Testimony,
    modifier: Modifier = Modifier
) {
    OutlinedCard (
        modifier = Modifier.wrapContentSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .width(300.dp)
                .padding(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = null,
                tint = colorResource(R.color.receive_money)
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = stringResource(id = testimony.userCommentRes)
            )
            Spacer(modifier = Modifier.size(20.dp))
            HorizontalDivider(
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Image(
                    painter = painterResource(id = testimony.userImageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = stringResource(id = testimony.userNameRes))
            }
        }
    }

}

@Composable
fun TestimoniesList() {
    Row(Modifier.horizontalScroll(rememberScrollState())) {
        DataProvider.listOfComment.forEach {
            TestimonyItem(testimony = it)
        }
    }

}
@Preview(widthDp = 300, heightDp = 500, showSystemUi = true)
@Composable
private fun PreviewCustomItem() {
    TestimoniesList()
}


