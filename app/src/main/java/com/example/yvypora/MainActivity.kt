package com.example.yvypora


import android.graphics.fonts.Font
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top

                    ){
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
fun MainLayout(){
    Column(
        modifier =
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        //Main
            Text(
                text = stringResource(id = R.string.slogan),
                fontSize = 45.sp,
                color = androidx.compose.ui.graphics.Color.White,
                letterSpacing = 4.sp
            )
        }

    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .width(50.dp)
            .height(50.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(Color(83,141,34))
    ){
        Text(
            text = stringResource(id = R.string.login),
            color = Color.White
        )
    }
        

}