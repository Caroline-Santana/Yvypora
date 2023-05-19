package com.example.yvypora.ScreenClients

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.yvypora.R
import com.example.yvypora.ui.theme.YvyporaTheme
import kotlinx.coroutines.delay

class LiveScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                        MainLive()
                }
            }
        }
    }
}

@Composable
fun MainLive() {
    val context = LocalContext.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.construction))
//    var isPlaying by remember { mutableStateOf(false) }
//    val progress by animateLottieCompositionAsState(
//        composition = composition,
//        isPlaying = isPlaying
//    )

//    LaunchedEffect(isPlaying )
//    {
//        while (true){
//            isPlaying = true
//            delay(1_000L)
//        }
//    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardAttention()
        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever

        )
    }
//    DisposableEffect(Unit){
//        onDispose {
//            composition?.let {
//                it.dispose()
//            }
//        }
//    }
}

@Composable
fun CardAttention() {
    Box(
        modifier = Modifier
            .width(243.dp)
            .height(170.dp)
            .shadow(5.dp, shape = RoundedCornerShape(5.dp))
            .background(colorResource(id = R.color.yellow_board), shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(240.dp)
                .height(170.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp, Color.Black),
            backgroundColor = colorResource(id = R.color.yellow_board),
            elevation = 5.dp
        ) {
            Column(modifier = Modifier
                .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                    backgroundColor = Color.Black
                ) {
                        Text(
                            text = stringResource(id = R.string.attention),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp),
                            fontSize = 39.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.yellow_board),
                            textAlign = TextAlign.Center
                        )
                }
                Text(
                    text = stringResource(id = R.string.message_attention),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 27.sp
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    YvyporaTheme {
        MainLive()
    }
}