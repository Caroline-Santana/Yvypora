package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.yvypora.model.template
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

class InicialScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_no_name),
                        modifier = Modifier
                            .height(58.dp)
                            .width(55.dp)
                            .padding(horizontal = 28.dp),
                        alignment = Alignment.BottomStart,
                        contentDescription = "logo",

                        )
                    Image(
                        painter = painterResource(id = R.drawable.icon_user),
                        modifier = Modifier
                            .height(50.dp)
                            .width(55.dp)
                            .padding(top = 5.dp, end = 15.dp),
                        alignment = Alignment.BottomEnd,
                        contentDescription = "logo",

                        )
                }
                LayoutMain()
            }

        }
    }


}
@Composable
fun LayoutMain() {
        var searchState by remember {
        mutableStateOf("")
    }
    Column( modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(top = 125.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(

            value = searchState,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 25.dp, end = 25.dp)
            ,
            onValueChange = {
                searchState = it
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.transparentgreen_yvy),
                focusedIndicatorColor = colorResource(id = R.color.transparentgreen_yvy),
                unfocusedIndicatorColor = colorResource(id = R.color.transparentgreen_yvy),
                cursorColor = colorResource(id = R.color.darkgreen_yvy)
            ),
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.lupa_icon),
                    contentDescription = stringResource(id = R.string.lupa),
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                        .padding(end = 10.dp),
                    tint = Color.White
                )
            }
        )
        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.fruits_category_icon),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                contentDescription = "Fruit"
            )
            Image(
                painter = painterResource(id = R.drawable.vegetables_category_icon),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                contentDescription = "Vegetables"
            )
            Image(
                painter = painterResource(id = R.drawable.spices_category_icon),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                contentDescription = "Spices"
            )
            Image(
                painter = painterResource(id = R.drawable.others_category_icon),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                contentDescription = "Others"
            )

        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(
                text = stringResource(id = R.string.fruits),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.vegetable),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.spices),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.other),
                modifier = Modifier
                    .height(80.dp)
                    .width(70.dp),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }

}

@ExperimentalPagerApi
@Composable
fun AutoSliding() {
    val pagerState = rememberPagerState(pageCount = template.size, initialOffscreenLimit = 2)
    
    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600) )
        }
    }
    
    Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Auto Sliding", 
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
                Spacer(modifier = Modifier.height(30.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .padding(0.dp, 40.dp, 0.dp, 40.dp)
                ) {page ->
                    Card(modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }

                        }
                        .fillMaxWidth()
                        .padding(15.dp, 0.dp, 15.dp, 0.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        val template = template[page]
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                                .align(Alignment.Center)
                        ){
//                            Image(painter = painterResource(
//                                id = when(page){
//                                    1 -> R.drawable.logo
//                                    2 -> R.drawable.ofertas
//                                    3 -> R.drawable.ofertas
//                                    4 -> R.drawable.ofertas
//                                    5 -> R.drawable.ofertas
//                                    else -> R.drawable.logo
////                                }
//                            ) )
                        }
                    }

                }
    }
}




