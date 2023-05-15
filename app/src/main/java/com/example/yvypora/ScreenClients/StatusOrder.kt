package com.example.yvypora.ScreenClients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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

                }
            }
        }
    }
}

enum class StatusPedido{

}
@Composable
fun StatusOrderMain() {
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
        Timeline()
    }

}

@Composable
fun Timeline(){
    Column(modifier = Modifier
        .height(600.dp)
        .padding(start = 40.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row() {
            Box(modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .background(
                    color = colorResource(id = R.color.green_button),
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
                    tint = Color.White
                )
            }

        }
        Spacer(modifier = Modifier.height(91.dp))
        Row() {
            Box(modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .background(
                    color = colorResource(id = R.color.green_button),
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
                        tint = Color.White
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
        }
        Spacer(modifier = Modifier.height(91.dp))
        Row(){
            Box(modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .background(
                    color = colorResource(id = R.color.gray_widht),
                    shape = RoundedCornerShape(5.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.entrega),
                    modifier = Modifier.height(25.dp).width(30.dp),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(91.dp))
        Row(){
            Box(modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .background(
                    color = colorResource(id = R.color.gray_widht),
                    shape = RoundedCornerShape(5.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.smile),
                    modifier = Modifier.height(25.dp).width(30.dp),
                    contentDescription = null,
                    tint = Color.Black
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