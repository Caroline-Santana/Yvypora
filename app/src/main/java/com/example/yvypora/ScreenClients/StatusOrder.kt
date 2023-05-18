package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.yvypora.ui.theme.YvyporaTheme

class StatusOrder : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StatusOrderMain()
                }
            }
        }
    }
}

enum class StatusPedido{
    CONFIRMADO, AGUARDANDO_RETIRADA, RETIRADO, SOB_CONFIRMACAO
}
@Composable
fun StatusOrderMain() {
    var statusPedido by remember{ mutableStateOf(StatusPedido.SOB_CONFIRMACAO) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)
        .fillMaxSize())
    {
        Text(
            text = stringResource(id = R.string.status_order),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.green_options),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(60.dp))
        Timeline(statusAtual = statusPedido)
    }

}
@Composable
fun CardConfirmDelivery(){
        Card(
            Modifier
                .width(345.dp)
                .height(111.dp),
            backgroundColor = colorResource(id = R.color.green_width),
            shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 0.dp,
                bottomEnd = 10.dp,
                bottomStart = 0.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.question_confirm_delivery),
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier
                            .height(45.dp)
                            .width(96.dp),
                        colors = ButtonDefaults.buttonColors(Color(36, 85, 1, 255)),
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(id = R.string.yes),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                    Button(
                        modifier = Modifier
                            .height(45.dp)
                            .width(96.dp),
                        colors = ButtonDefaults.buttonColors(Color(202, 14, 14, 255)),
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(id = R.string.no),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }

}
@Composable
fun Timeline(statusAtual : StatusPedido){
    Column(modifier = Modifier
        .height(600.dp)
        .verticalScroll(rememberScrollState())
        .padding(start = 30.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .background(
                    color =
                    if (statusAtual >= StatusPedido.CONFIRMADO)
                        colorResource(id = R.color.green_button)
                    else
                        colorResource(id = R.color.gray_circle),
                    shape = RoundedCornerShape(5.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.checkmark_outline_),
                    modifier = Modifier
                        .height(27.dp)
                        .width(24.dp),
                    contentDescription = null ,
                    tint =
                    if (statusAtual >= StatusPedido.CONFIRMADO)
                        Color.White
                    else
                        Color.Black
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    modifier = Modifier.size(35.dp),
                    shape = CircleShape,
                    backgroundColor =
                    if (statusAtual >= StatusPedido.CONFIRMADO)
                        Color.White
                    else
                        colorResource(id = R.color.gray_circle),

                    border = BorderStroke(3.dp,
                        if (statusAtual >= StatusPedido.CONFIRMADO)
                        colorResource(id = R.color.green_button)
                    else
                        colorResource(id = R.color.gray_stroke_circle))
                ){}
                Box(modifier = Modifier.size(4.dp, 100.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(6.dp)
                            .background(
                                if (statusAtual <= StatusPedido.CONFIRMADO)
                                    colorResource(id = R.color.gray_line)
                                else
                                    colorResource(id = R.color.green_button)

                            )
                    ) {}
            }
        }
            CardTimeLine(
                title = stringResource(id = R.string.pedido_confirmado),
                date = "03-02-2023",
                hour = "10:15 AM",
                atualStatus =  statusAtual >= StatusPedido.CONFIRMADO  || statusAtual == StatusPedido.CONFIRMADO
            )
        }
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .background(
                    color =
                    if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                        colorResource(id = R.color.green_button)
                    else
                        colorResource(id = R.color.gray_circle),
                    shape = RoundedCornerShape(5.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.array_down) ,
                        contentDescription = null,
                        tint =
                        if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                            Color.White
                        else
                            Color.Black
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sacola),
                        modifier = Modifier
                            .height(22.dp)
                            .width(22.dp)
                            .padding(bottom = 1.dp),
                        contentDescription = null,
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    modifier = Modifier.size(35.dp),
                    shape = CircleShape,
                    backgroundColor =
                    if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                        Color.White
                    else
                        colorResource(id = R.color.gray_circle),
                    border = BorderStroke(3.dp,
                        if (statusAtual >= StatusPedido.AGUARDANDO_RETIRADA)
                        colorResource(id = R.color.green_button)
                    else
                        colorResource(id = R.color.gray_stroke_circle))
                ){}
                Box(modifier = Modifier.size(4.dp, 100.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(6.dp)
                            .background(
                                if (statusAtual <= StatusPedido.AGUARDANDO_RETIRADA)
                                    colorResource(id = R.color.gray_line)
                                else
                                    colorResource(id = R.color.green_button)
                            )
                    ) {}
                }
            }
            CardTimeLine(
                title = stringResource(id = R.string.aguardando_retirada),
                date = "03-02-2023",
                hour = "10:15 AM",
                atualStatus =  statusAtual >= StatusPedido.AGUARDANDO_RETIRADA  || statusAtual == StatusPedido.AGUARDANDO_RETIRADA
            )
        }
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Box(modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .background(
                    color =
                    if (statusAtual >= StatusPedido.RETIRADO)
                        colorResource(id = R.color.green_button)
                    else
                        colorResource(id = R.color.gray_circle),
                    shape = RoundedCornerShape(5.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.entrega),
                    modifier = Modifier
                        .height(25.dp)
                        .width(30.dp),
                    contentDescription = null,
                    tint =
                    if (statusAtual >= StatusPedido.RETIRADO)
                        Color.White
                    else
                       Color.Black
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Card(
                    modifier = Modifier.size(35.dp),
                    shape = CircleShape,
                    backgroundColor =
                    if (statusAtual >= StatusPedido.RETIRADO)
                        Color.White
                    else
                        colorResource(id = R.color.gray_circle),
                    border = BorderStroke(3.dp,
                        if (statusAtual >= StatusPedido.RETIRADO)
                            colorResource(id = R.color.green_button)
                        else
                            colorResource(id = R.color.gray_stroke_circle)
                    )
                ){}
                Box(modifier = Modifier.size(4.dp, 100.dp)) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(6.dp)
                            .background(
                                if (statusAtual <= StatusPedido.RETIRADO)
                                    colorResource(id = R.color.gray_line)
                                else
                                    colorResource(id = R.color.green_button)

                            )
                    ) {}
                }
            }
            CardTimeLine(
                title = stringResource(id = R.string.pedido_retirado),
                date = "03-02-2023",
                hour = "10:15 AM",
                atualStatus = statusAtual >= StatusPedido.RETIRADO  || statusAtual == StatusPedido.RETIRADO
            )
        }
        Column() {
            Row( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Box(modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .background(
                        color =
                        if (statusAtual >= StatusPedido.SOB_CONFIRMACAO)
                            colorResource(id = R.color.green_button)
                        else
                            colorResource(id = R.color.gray_circle),
                        shape = RoundedCornerShape(5.dp)
                    ),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.smile),
                        modifier = Modifier
                            .height(25.dp)
                            .width(30.dp),
                        contentDescription = null,
                        tint =
                        if (statusAtual >= StatusPedido.SOB_CONFIRMACAO)
                            Color.White
                        else
                            Color.Black
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier.size(35.dp),
                        shape = CircleShape,
                        backgroundColor =
                        if (statusAtual >= StatusPedido.SOB_CONFIRMACAO)
                            Color.White
                        else
                            colorResource(id = R.color.gray_circle),
                        border = BorderStroke(3.dp,
                            if (statusAtual <= StatusPedido.RETIRADO)
                                colorResource(id = R.color.gray_stroke_circle)
                            else
                                colorResource(id = R.color.green_button)
                        )

                    ){}
                }
                CardTimeLine(
                    title = stringResource(id = R.string.sob_confirmacao_entrega),
                    date = "03-02-2023",
                    hour = "10:15 AM",
                    atualStatus = statusAtual >= StatusPedido.SOB_CONFIRMACAO  || statusAtual == StatusPedido.SOB_CONFIRMACAO
                )
            }
            if (statusAtual == StatusPedido.SOB_CONFIRMACAO)
                CardConfirmDelivery()
        }
        }
}

@Composable
fun CardTimeLine(title: String, date: String, hour: String, atualStatus : Boolean){
    var colorText: Color = colorResource(id = R.color.green_options)
    var othercolorText: Color = colorResource(id = R.color.gray_title_no_selecioned)

    Card(
        Modifier
            .width(243.dp)
            .height(75.dp),
        backgroundColor = Color.White,
        border = BorderStroke(2.dp, 
            if (atualStatus)
                colorResource(id = R.color.green_button)
        else   colorResource(id = R.color.gray_stroke_circle)
        ),
        shape = RoundedCornerShape(8.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 15.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                color =
                if (atualStatus)
                   colorText
                else othercolorText,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 5.dp, end = 10.dp)
                ,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = date,
                    textAlign = TextAlign.Start,
                    color =
                    if (atualStatus)
                        colorText
                    else othercolorText,
                    fontSize = 15.sp
                )
                Text(
                    text = hour,
                    textAlign = TextAlign.Start,
                    color =
                    if (atualStatus)
                        colorText
                    else othercolorText,
                    fontSize = 15.sp
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun StatusOrderPreview() {
    YvyporaTheme {
       StatusOrderMain()
    }
}