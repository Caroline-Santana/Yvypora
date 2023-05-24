package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.yvypora.R
import com.example.yvypora.domain.models.Entregador
import com.example.yvypora.ui.theme.YvyporaTheme

class AcompCorridaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainAcompCorrida()
                }
            }
        }
    }
}

@Composable
fun MainAcompCorrida() {
    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 15.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = stringResource(id = R.string.my_order),
                modifier = Modifier
                    .padding(bottom = 3.dp, end = 38.dp)
                    .fillMaxWidth(),
                fontSize = 24.sp,
                color = colorResource(id = R.color.full_dark_yvy),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        MapAcompCorrida()
        CardTeste()
    }
}

@Composable
fun CardTeste() {
    val context = LocalContext.current
    //        var nameCard = entregador.name
//        var distancia = entregador.distancia
//        var comprimento  = entregador.medida_comprimento
    var photo = painterResource(id = R.drawable.icon_entregador)
//        var tempo = entregador.tempo_chegada
//       var rating by remember{mutableStateOf(entregador.score_rating)}
    var nameCard = "João Souza"
    var distancia = 33
    var comprimento = "km"
    var tempo = "20"
    var rating by remember{mutableStateOf(2)}

    Card(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 8.dp)
            .width(394.dp)
            .height(211.dp),
        elevation = 1.dp,
        backgroundColor = colorResource(id = R.color.green_camps),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 0.dp,
            bottomEnd = 10.dp,
            bottomStart = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(top = 15.dp, start = 15.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row() {
                Image(
                    modifier = Modifier
                        .width(83.dp)
                        .height(86.dp),
                    painter = painterResource(id = R.drawable.icon_entregador),
                    contentDescription = null
                )
                Column(modifier = Modifier.padding(bottom = 5.dp)) {
                    Text(
                        text = nameCard,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp),
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.darkgreen_yvy),
                        fontSize = 22.sp
                    )
                    RatingEntregador(score = rating)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 66.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.distance),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            color = colorResource(id = R.color.darkgreen_yvy)
                        )
                        Text(
                            text = "${distancia} ${comprimento}",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            color = colorResource(id = R.color.darkgreen_yvy)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = R.string.estimated_time),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            color = colorResource(id = R.color.darkgreen_yvy)
                        )
                        Text(
                            text = "${tempo} minutos",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            color = colorResource(id = R.color.darkgreen_yvy)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 29.dp, end = 26.dp, top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier
                        .height(47.dp)
                        .width(147.dp),
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 0.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 0.dp
                    ),
                    border = BorderStroke(3.dp, colorResource(id = R.color.green_button)),
                    colors = ButtonDefaults.buttonColors(Color(255, 255, 255, 255)),
                    onClick = {}
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.message_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .height(25.dp)
                                .width(25.dp)
                                .padding(end = 3.dp),
                            tint = colorResource(id = R.color.green_yvy)
                        )
                        Text(
                            text = stringResource(id = R.string.message),
                            color = colorResource(id = R.color.green_yvy),
                            fontSize = 15.sp
                        )
                    }

                }
                Button(
                    modifier = Modifier
                        .height(47.dp)
                        .width(147.dp),
                    shape = RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 0.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(Color(83, 141, 34, 255)),
                    onClick = {}
                ){
                    Text(
                        text = stringResource(id = R.string.call),
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

val entregadorDados = mutableStateOf<com.example.yvypora.domain.models.Entregador>(
    com.example.yvypora.domain.models.Entregador(
        name = "João Souza",
        photo = "",
        distancia = 3,
        medida_comprimento = "km",
        tempo_chegada = 20,
        score_rating = 5
    )
)
@Composable
fun CardEntregador() {
    val context = LocalContext.current
//        var nameCard = entregador.name
//        var distancia = entregador.distancia
//        var comprimento  = entregador.medida_comprimento
        var photo = painterResource(id = R.drawable.icon_entregador)
//        var tempo = entregador.tempo_chegada
//       var rating by remember{mutableStateOf(entregador.score_rating)}
    var nameCard = "João Souza"
    var distancia = 33
    var comprimento = "km"
    var tempo = "20"
    var rating by remember{mutableStateOf(2)}

    Card(modifier = Modifier
        .padding(start = 5.dp, end = 5.dp, top = 8.dp)
        .width(394.dp)
        .height(211.dp),
        elevation = 1.dp,
        backgroundColor = colorResource(id = R.color.green_camps),
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 0.dp,
            bottomEnd = 10.dp,
            bottomStart = 0.dp
        )
    ) {

        Row(
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(83.dp)
                    .height(86.dp),
                painter = painterResource(id = R.drawable.icon_entregador),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 66.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.distance),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = "${distancia} ${comprimento}",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.estimated_time),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = "${tempo} minutos",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
        }
        Column() {
            Button(
                onClick = {
                    val intent = Intent(context, StatusOrder()::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .height(58.dp)
                    .width(58.dp)
                    .padding(start = 192.dp, end = 30.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 13.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 13.dp
                ),
                colors = ButtonDefaults.buttonColors(Color(36, 85, 1, 255)),
            ) {
                Text(
                    text = stringResource(id = R.string.pay),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}


@Composable
fun MapAcompCorrida(){
    Card(modifier = Modifier
        .width(407.dp)
        .height(348.dp),
        backgroundColor = colorResource(id = R.color.green_yvy),
    )
    {
        Text(text = "MAPA AQUI")
    }

}

@Composable
fun StarEntregador(isFilled: Boolean){
    if (isFilled){
        Icon(
            painter = painterResource(id = R.drawable.star_entregador),
            modifier = Modifier
                .height(24.dp)
                .padding(end = 5.dp)
                .width(24.dp),
            contentDescription = "Star",
            tint = colorResource(id = R.color.yellow_star_entregador)
        )
    }else{
        Icon(
            painter = painterResource(id = R.drawable.star_entregador),
            contentDescription = "Star",
            modifier = Modifier
                .height(24.dp)
                .padding(end = 5.dp)
                .width(24.dp),
            tint = colorResource(id = R.color.gray_star)
        )
    }

}

@Composable
fun RatingEntregador(score: Int){
    Row {
        repeat(score){
            StarEntregador(isFilled = true)
        }
        repeat(5 - score){
            StarEntregador(isFilled = false)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AcompCorridaPreview() {
    YvyporaTheme {
       MainAcompCorrida()
    }
}