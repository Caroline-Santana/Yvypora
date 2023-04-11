package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ui.theme.YvyporaTheme

class AdressesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        MainAddress()
                    }
                }
            }
        }
    }
}

@Composable
fun MainAddress() {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 15.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                modifier = Modifier
                    .height(45.dp)
                    .width(55.dp)
                    .clickable {
                        val intent = Intent(context, ProfileClient()::class.java)
                        context.startActivity(intent)
                    },
                alignment = Alignment.BottomStart,
                contentDescription = stringResource(id = R.string.back_screen)
            )
            Text(
                text = stringResource(id = R.string.adresses),
                modifier = Modifier
                    .padding(bottom = 3.dp, end = 38.dp)
                    .fillMaxWidth(),
                fontSize = 24.sp,
                color = colorResource(id = R.color.darkgreen_yvy),
                textAlign = TextAlign.Center
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp, start = 28.dp, end = 25.dp),
            verticalArrangement = Arrangement.Center)
        {
            CardPrincipalAdresses()
            CardAdresses()
            CardAdressesTeste()

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(id = R.string.add_new_adress),
                modifier = Modifier
                    .padding(bottom = 3.dp)
                    .fillMaxWidth(),
                fontSize = 16.sp,
                color = colorResource(id = R.color.darkgreen_yvy),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    val intent = Intent(context, AddAdressAcitivity()::class.java)
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
                    text = stringResource(id = R.string.to_add),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }

    }
}
@Composable
fun CardPrincipalAdresses(){
    Column(modifier = Modifier.padding(top = 23.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 190.dp, end = 7.dp)
                .height(21.dp)
                .background(
                    color =
                    colorResource(id = R.color.green_yvy),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 10.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 10.dp
                    )
                )
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.green_yvy),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 10.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 10.dp
                    )
                ),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                text = stringResource(id = R.string.main_adresses),
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.green_camps)

            )
        }
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
            .height(140.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.darkgreen_yvy),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 10.dp
                )
            ),
            backgroundColor = colorResource(id = R.color.green_camps),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 10.dp
            )
        ) {
            Column() {
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.house2),
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                        ,
                        tint = colorResource(id = R.color.darkgreen_yvy),
                        contentDescription = "icon"
                    )
                    Text(
                        text = "Casa",
                        modifier = Modifier
                            .padding(start = 6.dp),
                        fontSize = 23.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    OpcoesMenu()
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Carlos Arcanjo",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                    Text(
                        text = " ${'-'} carlao*****@gmail.com",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Rua Oscar Freire",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = "nº126${','}",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = " São Paulo${','}",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }

                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = " São Paulo${','}",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = " Brasil",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
        }
    }
}
@Composable
fun CardAdresses(){
    Column(modifier = Modifier.padding(top = 15.dp)) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
            .height(140.dp),
            backgroundColor = colorResource(id = R.color.green_camps),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 10.dp
            )
        ) {
            Column() {
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.house2),
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                        ,
                        tint = colorResource(id = R.color.darkgreen_yvy),
                        contentDescription = "icon"
                    )
                    Text(
                        text = "Escritório",
                        modifier = Modifier
                            .padding(start = 6.dp),
                        fontSize = 23.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    OpcoesMenu()
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Carlos Arcanjo",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                    Text(
                        text = " ${'-'} carlao*****@gmail.com",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Rua Oscar Freire",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = "nº126${','}",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = " São Paulo${','}",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }

                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = " São Paulo${','}",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = " Brasil",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
        }
    }
}
@Composable
fun CardAdressesTeste(){
    Column(modifier = Modifier.padding(top = 15.dp)) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
            .height(140.dp),
            backgroundColor = colorResource(id = R.color.green_camps),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 10.dp,
                bottomEnd = 0.dp,
                bottomStart = 10.dp
            )
        ) {
            Column() {
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.house2),
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                        ,
                        tint = colorResource(id = R.color.darkgreen_yvy),
                        contentDescription = "icon"
                    )
                    Text(
                        text = "Escritório",
                        modifier = Modifier
                            .padding(start = 6.dp),
                        fontSize = 23.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    OpcoesMenu()
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Carlos Arcanjo",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                    Text(
                        text = " ${'-'} carlao*****@gmail.com",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)

                    )
                }
                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Rua Oscar Freire",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = "nº126${','}",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = " São Paulo${','}",
                        modifier = Modifier
                            .padding(start = 2.dp),
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }

                Row(modifier = Modifier
                    .padding(start = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = " São Paulo${','}",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                    Text(
                        text = " Brasil",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
            }
        }
    }
}

@Composable
fun OpcoesMenu() {
    var showPopup by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ) {
        IconButton(
            onClick = { showPopup = true },
            modifier = Modifier
                .height(40.dp)
        ) {
            Icon(
                Icons.Filled.MoreVert,
                contentDescription = "Mais opções",
                tint = colorResource(id = R.color.green_options)
            )
        }

        DropdownMenu(
            expanded = showPopup,
            offset = DpOffset(x = (140).dp, y = (5).dp),
            onDismissRequest = { showPopup = false }
        ) {
            DropdownMenuItem(onClick = { /* ação 1 */ }) {
                Text(
                    text = "Opção 1",
                    color = colorResource(id = R.color.green_yvy)
                )
            }
            DropdownMenuItem(onClick = { /* ação 2 */ }) {
                Text(text = "Opção 2",
                    color = colorResource(id = R.color.green_yvy)
                )
            }
        }
    }



}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    YvyporaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MainAddress()
        }
    }
}