@file:OptIn(ExperimentalPagerApi::class)

package com.example.yvypora



import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.yvypora.model.template
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
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
                        .padding(top = 40.dp)
                        .background(color = Color.Unspecified),

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
                UpsideLayout()
            }

        }
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UpsideLayout() {
    var searchState by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 125.dp)
            .verticalScroll(state = rememberScrollState(), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchState,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 25.dp, end = 25.dp),
            onValueChange = {
                searchState = it
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.transparentgreen_yvy),
                focusedIndicatorColor = colorResource(id = R.color.green_yvy),
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
        // Função dos atalhos para outras telas
             Shortcuts()

    }

        AutoSliding()
        TabLayoutScreen()
        CardProducts()


}

@Composable
fun TabLayoutScreen() {

    val tabData = listOf(
         stringResource(id = R.string.all),
        stringResource(id = R.string.discount),
        stringResource(id = R.string.near)
    )
    val pagerState = rememberPagerState(
        pageCount = tabData.size,
        initialOffscreenLimit = 2,
        infiniteLoop = true,
        initialPage = 1,
    )
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column (modifier = Modifier
        .padding(top = 552.dp)){
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            backgroundColor =Color.Unspecified,
            contentColor =  colorResource(id = R.color.green_yvy),

        ) {
            tabData.forEachIndexed { index, pair ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tabData[index],
                        )
                    },
                    selectedContentColor = colorResource(id = R.color.green_yvy),
                    unselectedContentColor = colorResource(id = R.color.darkgreen_yvy),
                ) 
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                tabData[0]
            }
        }
    }
}




@Composable
fun CardProducts() {
        var titleCard = "Abobrinha"
        var photoProduct = painterResource(id = R.drawable.abobrinha)
        var qtdeProduct = "200g"
        var priceProduct = "R$6,00"
        val colorCircle = colorResource(id = R.color.darkgreen_yvy)
    Card(
        elevation = 10.dp,
        contentColor = colorResource(id = R.color.darkgreen_yvy),
        modifier = Modifier
            .width(130.dp)
            .height(160.dp),
        border = BorderStroke(1.dp,colorResource(id = R.color.green_yvy))

    ) {
        Column{
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = titleCard,
                    modifier = Modifier.padding(top = 4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Image(
                    painter = photoProduct,
                    contentDescription = "Product",
                    modifier = Modifier
                        .width(97.dp)
                        .height(70.dp),
                )
            }
            Text(
                text = qtdeProduct,
                modifier = Modifier.padding(top = 2.dp, start = 4.dp),
                color = colorResource(id = R.color.dark_gray),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                fontSize = 12.sp
            )

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = priceProduct,
                    modifier = Modifier.padding(top = 2.dp, start = 12.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    fontSize = 14.sp
                )
                Icon(
                    painter = painterResource(R.drawable.shopping_cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .drawBehind {
                            drawCircle(
                                color = colorCircle,
                                radius = 65F,
                                center = Offset.VisibilityThreshold
                            )
                        },
                    contentDescription = "Shopping",
                    tint = Color.White,

                )
            }

        }
    }
}
@Composable
fun Shortcuts(){
    Row(
        modifier = Modifier
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    )
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
@ExperimentalPagerApi
@Composable
fun AutoSliding() {
    val pagerState = rememberPagerState(pageCount = template.size, initialOffscreenLimit = 2)

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(modifier = Modifier.padding(top = 60.dp)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
        ) { page ->
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
                .height(195.dp)
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                val template = template[page]
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .width(130.dp)
                        .align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(
                            id = when (page) {
                                1 -> R.drawable.logo
                                2 -> R.drawable.ofertas
                                3 -> R.drawable.ofertas
                                4 -> R.drawable.ofertas
                                5 -> R.drawable.ofertas
                                else -> R.drawable.logo
                            }
                        ), contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(15.dp)
                    ) {
                        val ratingBar = RatingBar(
                            LocalContext.current,
                            null,
                            androidx.appcompat.R.attr.ratingBarStyleSmall
                        ).apply {
                            rating = template.rating
                        }
                        AndroidView(
                            factory = { ratingBar },
                            modifier = Modifier
                                .padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )
                    }
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(top = 235.dp),
                activeColor = colorResource(id = R.color.darkgreen_yvy),
                inactiveColor = colorResource(id = R.color.transparentgreen_yvy)
            )
        }
    }
}





