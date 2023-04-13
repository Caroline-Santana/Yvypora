package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.yvypora.api.commons.auth
import com.example.yvypora.models.Credentials
import com.example.yvypora.ui.theme.YvyporaTheme

class DescriptionProducts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
                        HeaderDescriptionProducts()
                }
            }
        }
    }
}

@Composable
fun HeaderDescriptionProducts() {
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .statusBarsPadding(),
        contentAlignment = Alignment.TopStart
    ){
        Image(
            painter = painterResource(id = R.drawable.wave_products),
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp),
            contentDescription = stringResource(id = R.string.back_screen)
        )
        Image(
            painter = painterResource(id = R.drawable.arrow_white),
            modifier = Modifier
                .height(65.dp)
                .width(65.dp)
                .padding(start = 15.dp, top = 30.dp)
                .clickable {
                    val intent = Intent(context, InicialScreen::class.java)
                    context.startActivity(intent)
                },
            alignment = Alignment.BottomStart,
            contentDescription = stringResource(id = R.string.back_screen)
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.beterraba),
                modifier = Modifier
                    .height(265.dp)
                    .width(260.dp),
                contentDescription = "product",
                alignment = Alignment.Center
            )
            Text(
                text = "Beterraba",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                fontSize = 32.sp,
                color = colorResource(id = R.color.green_text_dark)
            )
            Text(
                text = "ZédoAlfácil",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                fontSize = 18.sp,
                color = colorResource(id = R.color.gray_yvy)
            )
            MainDescriptionProducts()
        }


    }

}

@Composable
fun MainDescriptionProducts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 27.dp, start = 15.dp)
    ) {
        Text(
            text = "R$7,50",
            fontSize = 36.sp,
            color = colorResource(id = R.color.green_options)
        )
        Text(
            text = stringResource(id = R.string.description),
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 32.sp,
            color = colorResource(id = R.color.green_text_dark)
        )
        Text(
            text = "A beterraba é uma planta herbácea da família das Quenopodiáceas," +
                    " por Cronquist, ou das Amarantáceas, pela APG. Nome derivado do " +
                    "substantivo francês betterave. O colo tuberizado serve, para além " +
                    "dos fins culinários, produção de açúcar.",
            modifier = Modifier.padding(top = 15.dp, end = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            fontSize = 15.sp,
            color = colorResource(id = R.color.green_text_dark)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 45.dp, end = 45.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(128.dp)
                    .height(39.dp),
                border = BorderStroke(1.dp, colorResource(id = R.color.green_button)),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(255, 255, 255, 255)),


                ) {
                Text(
                    text = stringResource(id = R.string.add_affection),
                    color = colorResource(id = R.color.green_button),
                    fontSize = 12.sp
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .width(128.dp)
                    .height(39.dp)
                    ,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(115, 169, 66, 255)),


                ) {
                Text(
                    text = stringResource(id = R.string.buy_now),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionProductsPreview() {
    YvyporaTheme {

            HeaderDescriptionProducts()

    }
}