package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import kotlin.math.max
import androidx.compose.foundation.layout.*
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
    var rating by remember{mutableStateOf(4)}
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
            .padding(top = 90.dp),
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
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Beterraba",
                    modifier = Modifier
                        .padding(start = 8.dp),
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.green_text_dark)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Rating(score = rating)
                    Text(
                        text = "4.2",
                        modifier = Modifier.padding(start = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }


            }

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
fun Star(isFilled: Boolean){
    if (isFilled){
        Icon(
            painter = painterResource(id = R.drawable.star),
            modifier = Modifier
                .height(16.dp)
                .padding(end = 5.dp)
                .width(18.dp),
            contentDescription = "Star",
            tint = colorResource(id = R.color.yellow_star)
        )
    }else{
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "Star",
            modifier = Modifier
                .height(16.dp)
                .padding(end = 5.dp)
                .width(18.dp),
            tint = colorResource(id = R.color.gray_star)
        )
    }

}

@Composable
fun Rating(score: Int){
    Row {
       repeat(score){
           Star(isFilled = true)
       }
        repeat(5 - score){
            Star(isFilled = false)
        }
    }
}

@Composable
fun MainDescriptionProducts() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 10.dp, start = 15.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(
                text = "R$7,50",
                fontSize = 36.sp,
                modifier = Modifier.padding(end = 90.dp),
                color = colorResource(id = R.color.green_options)
            )
            WidhtProductTeste()
        }

        Text(
            text = stringResource(id = R.string.description),
            modifier = Modifier.padding(top = 10.dp),
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
            .padding(start = 35.dp, end = 45.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { },
                modifier = Modifier
                    .width(128.dp)
                    .height(45.dp),
                border = BorderStroke(1.dp, colorResource(id = R.color.green_button)),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(255, 255, 255, 255)),


                ) {
                Icon(
                    painter = painterResource(id = R.drawable.shopadd),
                    modifier = Modifier.padding(end = 3.dp),
                    contentDescription = "content description",
                    tint = colorResource(id =R.color.green_button )
                )
                Text(
                    text = stringResource(id = R.string.add_affection),
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(id = R.color.green_button),
                    fontSize = 12.sp
                )
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .width(128.dp)
                    .height(45.dp)
                    ,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(115, 169, 66, 255)),


                ) {
                Text(
                    text = stringResource(id = R.string.buy_now),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun WidhtProductTeste() {
    var weight by remember { mutableStateOf(1.0f) }
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.green_widht_transparent),
                RoundedCornerShape(7.dp)
            )
            .width(105.dp)
            .height(30.dp)
    ) {
        Row(
            modifier = Modifier
                .width(105.dp)
                .height(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { weight = (weight - 0.5f).coerceAtLeast(1f)},
                modifier = Modifier
                    .height(30.dp)
                    .width(32.dp),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(217, 217, 217, 255))
            ) {}

            Text(
            text = "$weight Kg",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.green_widht)
        )
            Box(contentAlignment = Alignment.Center) {
                Button(
                    onClick = { weight += 0.5f },
                    modifier = Modifier
                        .height(30.dp)
                        .width(32.dp),
                    shape = RoundedCornerShape(7.dp),
                ) {}
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    modifier = Modifier
                        .clickable { weight += 0.5f },
                    contentDescription = "",
                    tint = Color.White
                )
            }

        }

        Icon(
            painter = painterResource(id = R.drawable.remove),
            modifier = Modifier.clickable {  weight = (weight - 0.5f).coerceAtLeast(1f) },
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DescriptionProductsPreview() {
    YvyporaTheme {

            HeaderDescriptionProducts()

    }
}