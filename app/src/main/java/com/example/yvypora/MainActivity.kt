package com.example.yvypora


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.MarketerScreens.InicialMarketerActivity
import com.example.yvypora.ScreenClients.InicialScreen
import com.example.yvypora.api.commons.fieldsForCostumer
import com.example.yvypora.ui.theme.YvyporaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top

                    ) {
                        Text(
                            modifier = Modifier.padding(top = 35.dp, end = 10.dp),
                            text = stringResource(id = R.string.yvypora),
                            color = colorResource(id = R.color.green_yvy),
                            fontSize = 32.sp,
                        )

                    }


                }
                MainLayout()

            }

        }
    }
}




@Composable
fun MainLayout() {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //Main
        Text(
            text = stringResource(id = R.string.slogan),
            fontSize = 45.sp,
            color = androidx.compose.ui.graphics.Color.White,

            letterSpacing = 4.sp
        )
    }
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(bottom = 34.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        val context = LocalContext.current

        Button(onClick = {
            val intent = Intent(context, InicialScreen()::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "consumidor")
        }
        Button(onClick = {
            val intent = Intent(context, InicialMarketerActivity()::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "feirante")
        }



        Button(
            onClick = {
                val intent = Intent(context, LoginActivity()::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .width(330.dp)
                .height(135.dp)
                .padding(bottom = 85.dp)
                .padding(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color(83, 141, 34)),

            ) {
            Text(
                text = stringResource(id = R.string.login),
                color = Color.White,
                fontSize = 24.sp
            )
        }
        Text(
            text = stringResource(id = R.string.question),
            fontSize = 18.sp,
            color = androidx.compose.ui.graphics.Color.White,

            letterSpacing = 4.sp
        )
        ClickableText(
            text = AnnotatedString(text = stringResource(id = R.string.register),
               SpanStyle(
                   fontSize = 18.sp,
                    color = androidx.compose.ui.graphics.Color.White,
                    textDecoration = TextDecoration.Underline,
                    letterSpacing = 4.sp
               )
            ), onClick = {
            val intent = Intent(context, DecisionSreenActivity()::class.java)
            context.startActivity(intent)

        })
    }
}
@Preview(showBackground = true, showSystemUi = true)

@Composable
fun DefaultPreview() {
    YvyporaTheme{
        MainLayout()
    }
}

