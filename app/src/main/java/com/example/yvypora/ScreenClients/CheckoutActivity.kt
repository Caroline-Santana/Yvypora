package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.theme.YvyporaTheme

class CheckoutActivity : ComponentActivity() {
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
                        .fillMaxWidth())
                    {
                        Header()
                        MainCheckout()
                    }

                }
            }
        }
    }
}
//val selectedCards = mutableStateListOf<Int>()

@Composable
fun MainCheckout() {
 val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, start = 35.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow),
            modifier = Modifier
                .width(43.dp)
                .height(46.dp)
                .clickable {
                    val intent = Intent(context, ShoppingCartActivity()::class.java)
                    context.startActivity(intent)
                },
            contentDescription = "back",
            tint = colorResource(id = R.color.green_widht)
        )
        Text(
            text = stringResource(id = R.string.checkout),
            modifier = Modifier.width(250.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = colorResource(id = R.color.darkgreen_yvy2)
        )
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(top = 36.dp))
    {
        Row(
            modifier = Modifier
                .padding(start = 37.dp, end = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.address),
                fontSize = 22.sp,
                color = colorResource(id = R.color.darkgreen_yvy2)
            )
            ClickableText(
                text = AnnotatedString(text =  stringResource(id = R.string.change),
                    SpanStyle(
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green_camps),
                        textDecoration = TextDecoration.Underline,
                    )
                ), onClick = {
//                    val intent = Intent(context, DecisionSreenActivity()::class.java)
//                    context.startActivity(intent)

                })
        }


    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutPreview() {
    YvyporaTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth())
        {
            Header()
            MainCheckout()
        }
    }
}