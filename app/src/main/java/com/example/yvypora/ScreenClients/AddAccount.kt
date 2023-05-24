package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.domain.models.MarketerCardShopping
import com.example.yvypora.domain.models.ProductCardShopping
import com.example.yvypora.ui.theme.SpaceGrotesk
import com.example.yvypora.ui.theme.YvyporaTheme

class AddAccount : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AdAccount()
                }
            }
        }
    }
}

@Composable
fun AdAccount() {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.wave__5_),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        alignment = Alignment.TopCenter,
        contentDescription = stringResource(id = R.string.back_screen)
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 25.dp, end = 25.dp, top = 130.dp)
        .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.add_account_pay_methods),
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 24.sp,
            color = colorResource(id = R.color.green_yvy),
            textAlign = TextAlign.Center
        )
        NameInputCard()
        Spacer(modifier = Modifier.height(15.dp))
        NumberInputCard()
        Spacer(modifier = Modifier.height(15.dp))
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            CvvInputCard()
            DateInputCard()
        }
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                val intent = Intent(context, PaymentMethodsActivity()::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
            modifier = Modifier
                .width(217.dp)
                .height(48.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.to_save),
                color = Color.White,
                fontSize = 20.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    YvyporaTheme {

    }
}
