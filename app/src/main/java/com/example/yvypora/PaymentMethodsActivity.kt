package com.example.yvypora

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class PaymentMethodsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                    ) {
                        HeaderPayMethods()
                        TabLayoutScreenPayment()
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayoutScreenPayment() {
    val offset = remember { mutableStateOf(0f) }

    val tabData = listOf(
        stringResource(id = R.string.credit_card),
        stringResource(id = R.string.other),
    )
    val pagerState = rememberPagerState(
        pageCount = tabData.size,
        initialOffscreenLimit = 2,
        infiniteLoop = true,
        initialPage = 1,
    )
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            backgroundColor = Color.Unspecified,
            contentColor = colorResource(id = R.color.green_yvy),

            ) {
            tabData.forEachIndexed { index, pair ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tabData[index],
                        )
                    },
                    selectedContentColor = colorResource(id = R.color.green_yvy),
                    unselectedContentColor = colorResource(id = R.color.darkgreen_yvy),
                )
            }
        }
        HorizontalPager(
            state = pagerState, modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (tabIndex) {
                    0 -> CampCardCredit()
                    1 ->  CampAccount()
                }

            }
        }
    }

}

@Composable
fun CampAccount() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .fillMaxSize(),
    )
    {
        Text(
            text = stringResource(id = R.string.other),
            modifier = Modifier
                .padding(top = 30.dp, bottom = 15.dp)
                .fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        CardPayMethods3()
        Spacer(modifier = Modifier.height(15.dp))
        CardPayMethods4()

        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                val intent = Intent(context, AddCardAcitivity()::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
            modifier = Modifier
                .width(200.dp)
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.add_account),
                color = Color.White,
                fontSize = 20.sp
            )
        }

    }
}


@Composable
fun CampCardCredit() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .fillMaxSize(),
    )
    {
        Text(
            text = stringResource(id = R.string.credit_card),
            modifier = Modifier
                .padding(top = 30.dp, bottom = 15.dp)
                .fillMaxWidth(),
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
        CardPayMethods1()
        Spacer(modifier = Modifier.height(15.dp))
        CardPayMethods2()

        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                val intent = Intent(context, AddCardAcitivity()::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
            modifier = Modifier
                .width(200.dp)
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.add_card),
                color = Color.White,
                fontSize = 20.sp
            )
        }

    }
}

@Composable
fun HeaderPayMethods() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp, start = 15.dp, bottom = 15.dp, end = 30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow),
            modifier = Modifier
                .height(45.dp)
                .width(55.dp)
                .padding(start = 3.dp)
                .clickable {
                    val intent = Intent(context, ProfileClient()::class.java)
                    context.startActivity(intent)
                },
            alignment = Alignment.BottomStart,
            contentDescription = stringResource(id = R.string.back_screen)
        )
        Text(
            text = stringResource(id = R.string.title_payment_methods),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.darkgreen_yvy)
        )
    }
}



@Composable
fun CardPayMethods1() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = R.drawable.visa),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
            ,
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "VISA",
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}
@Composable
fun CardPayMethods2() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = R.drawable.mastercard),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
            ,
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "MasterCard",
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}
@Composable
fun CardPayMethods3() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                val intent = Intent(context, PaymentMethodsActivity()::class.java)
                context.startActivity(intent)
            }
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = R.drawable.paypal),
            modifier = Modifier
                .width(30.dp)
                .height(35.dp)
            ,
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "PayPal",
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}
@Composable
fun CardPayMethods4() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                val intent = Intent(context, PaymentMethodsActivity()::class.java)
                context.startActivity(intent)
            }
            .background(colorResource(id = R.color.green_camps))
            .padding(start = 25.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = R.drawable.apple_pay),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
            ,
            contentDescription = "icon"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ApplePAy",
                modifier = Modifier
                    .padding(start = 10.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.darkgreen_yvy)

            )
            Icon(
                painter = painterResource(id = R.drawable.arrowright2),
                modifier = Modifier
                    .width(34.dp)
                    .height(34.dp)
                ,
                tint = colorResource(id = R.color.darkgreen_yvy),
                contentDescription = "icon"
            )
        }

    }
}

@Preview(showBackground = true)

@Composable
    fun PaymentMethodsActivityPreview() {
        YvyporaTheme {
            Column(modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
            ) {
                HeaderPayMethods()
                TabLayoutScreenPayment()
            }
        }
    }
