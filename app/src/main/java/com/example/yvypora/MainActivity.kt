package com.example.yvypora

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.ui.theme.YvyporaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    Image(painter =  painterResource(id = R.drawable.background), contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = stringResource(id = R.string.slogan),
                            color = androidx.compose.ui.graphics.Color.White,
                            fontSize = 45.sp,


                        )
                    }


                }
            }
        }
    }
}

@Composable
fun MainLayout(){
    Column(
        modifier =
        Modifier.fillMaxSize()
    ) {
        //Header
            Text(
                text = stringResource(id = R.string.yvypora),
                fontSize = 32.sp,

                letterSpacing = 4.sp
            )
        }

}