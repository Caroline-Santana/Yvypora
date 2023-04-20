package com.example.yvypora

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ScreenClients.RegisterClient
import com.example.yvypora.ui.theme.YvyporaTheme

class DecisionSreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {

                Surface(
                    modifier = Modifier.fillMaxWidth(),

                ) {
                    Image(
                        painter =  painterResource(id =R.drawable.logo_no_name),
                        modifier = Modifier
                            .height(58.dp)
                            .width(55.dp)
                            .padding(horizontal = 28.dp),
                        alignment = Alignment.BottomEnd,
                        contentDescription = "logo",

                    )
                    Spacer(
                        modifier = Modifier.height(36.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        TitleWithStyle()
                        Spacer(
                            modifier = Modifier.height(36.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.frutas_e_verduras),
                            modifier = Modifier
                                .height(238.dp)
                                .fillMaxWidth()
                                .align(Alignment.Start)
                                .padding(end = 70.dp)
                                ,
                            contentDescription = "background"
                        )
                    }
                }
                FildDecision()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleWithStyle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.title1_activity_decision_sreen),
                modifier = Modifier.padding(top = 35.dp, end = 10.dp),
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.darkgreen_yvy),
                fontSize = 24.sp
            )
            Text(
                text = "Yvyporã,",
                color = colorResource(id = R.color.green_yvy),
                modifier = Modifier.padding(top = 35.dp, end = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
        Text(
            text = stringResource(id = R.string.title2_activity_decision_sreen),
            modifier = Modifier.padding(end = 84.dp),
            textAlign = TextAlign.Start,
            color = colorResource(id = R.color.darkgreen_yvy),
            fontSize = 24.sp
        )
    }
}

@Composable
fun FildDecision() {

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(
            modifier = Modifier.height(280.dp)
        )
        Text(
            text = stringResource(id = R.string.question_decision_sreen),
            modifier = Modifier.padding(start = 21.dp, top = 35.dp),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.darkgreen_yvy),
            fontSize = 20.sp
        )
        Spacer(
            modifier = Modifier.height(13.dp)
        )
        ButtonDecision()
    }
}

@Composable
fun ButtonDecision(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 22.dp, end = 39.dp)
    ) {
        val context = LocalContext.current
        Button(
            onClick = {
                      val intent = Intent(context, RegisterClient()::class.java)
                                    context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
            modifier = Modifier
                .width(160.dp)
                .height(58.dp)
                .padding(end = 9.dp),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.decision_option1),
                color = Color.White,
                fontSize = 20.sp
            )
        }
        Button(
            onClick = {
                val intent = Intent(context,RegisterMarketer()::class.java)
                context.startActivity(intent)
            },

            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),
            modifier = Modifier
                .width(160.dp)
                .height(58.dp)
                .padding(start = 9.dp),
            shape = RoundedCornerShape(5.dp),

            ) {
            Text(
                text = stringResource(id = R.string.decision_option2),
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}


