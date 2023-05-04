package com.example.yvypora.MarketerScreens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.yvypora.R
import com.example.yvypora.ScreenClients.InicialScreen
import com.example.yvypora.ScreenClients.ShoppingCartActivity
import com.example.yvypora.navbar.ItemsMenu
import com.example.yvypora.navbar.NavigationHost
import com.example.yvypora.ui.theme.YvyporaTheme
import kotlinx.coroutines.CoroutineScope

class InicialMarketerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                Surface() {
                    InicialMarketerMain()
                }

            }
        }
    }
}

@Composable
fun InicialMarketerMain() {
    val context = LocalContext.current
    var statePrice by remember { mutableStateOf(false) }
    val wave = painterResource(id = R.drawable.wave_marketer)
    val user = painterResource(id = R.drawable.icon_user)
    val total_sale = 100.56




/*
* TODO :
*   - INTEGRAR OS DADOS DA VENDA NEM QUE SEJAM MOCKADOS
*   - FAZER A NAVBAR DO FEIRANTE QUE É DIFERENTE DO CONSUMIDOR ou concertar a do consumidor e assim fazer
* a do feirante
*
*   E DEPOIS DE TERMINAR ESSA TELA, TEM QUE FAZER TUDO SOBRE PRODUTO
*
* */

    Column(Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.TopEnd) {
            Image(
                painter = wave, contentDescription = "",
                Modifier
                    .width(430.dp)
                    .height(176.dp)
                    .zIndex(1f)
            )
            Image(
                painter = user, contentDescription = "",
                Modifier
                    .width(105.dp)
                    .height(83.dp)
                    .zIndex(2f)
                    .padding(top = 20.dp, end = 30.dp)


            )
        }
        Card(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .shadow(
                    elevation = 50.dp,
                    shape = RoundedCornerShape(15.dp),
                    ambientColor = colorResource(id = R.color.green_yvy),
                    spotColor = colorResource(id = R.color.green_yvy)
                )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.title_card_inicial_marketer),
                    color = colorResource(
                        id = R.color.green_options
                    ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(id = R.string.description_card_inicial_marketer),
                    color = colorResource(
                        id = R.color.gray_text
                    ),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_products_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .width(80.dp)
                            .height(100.dp)
                            .clickable {}
                    )
                    Spacer(
                        modifier = Modifier
                            .width(1.7.dp)
                            .height(80.dp)
                            .background(
                                color = colorResource(id = R.color.green_width)
                            )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.fair_add_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .width(80.dp)
                            .height(100.dp)
                            .clickable {}
                    )
                }
            }
        }
        Card(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .shadow(
                    elevation = 50.dp,
                    shape = RoundedCornerShape(15.dp),
                    ambientColor = colorResource(id = R.color.green_yvy),
                    spotColor = colorResource(id = R.color.green_yvy)
                )
        ) {
            Column(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.your_sales_today),
                        color = colorResource(
                            id = R.color.green_options
                        ),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
                Text(
                    text = stringResource(id = R.string.total),
                    color = colorResource(
                        id = R.color.gray_text
                    ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
                Row(Modifier.fillMaxWidth()) {
                    if (statePrice) {
                        Text(
                            text = "R$$total_sale",
                            color = colorResource(
                                id = R.color.darkgreen_yvy
                            ),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,

                            )
                        Spacer(Modifier.padding(15.dp))
                        Image(
                            painter = painterResource(id = R.drawable.olho_off),
                            contentDescription = "",
                            Modifier
                                .clickable { statePrice = false }
                                .height(50.dp)
                                .width(50.dp)
                        )
                    } else {
                        if (total_sale<100){
                            Text(
                                text = "R$$****",
                                color = colorResource(
                                    id = R.color.darkgreen_yvy
                                ),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,

                                )
                        }
                        else{
                            Text(
                                text = "R$*****",
                                color = colorResource(
                                    id = R.color.darkgreen_yvy
                                ),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,

                                )
                        }
                        Spacer(Modifier.padding(15.dp))
                        Image(
                            painter = painterResource(id = R.drawable.olho_on),
                            contentDescription = "",
                            Modifier
                                .clickable { statePrice = true }
                                .height(50.dp)
                                .width(50.dp)
                        )
                    }
                }
               Row(Modifier.fillMaxWidth()) {
                   Text(text = "venda as 12:99", color = colorResource(id = R.color.gray_text))
                   Text(text = "+ 5,00", color = colorResource(id = R.color.green_yvy))
               }
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "venda as 12:99", color = colorResource(id = R.color.gray_text))
                    Text(text = "+ 5,00", color = colorResource(id = R.color.green_yvy))
                }
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "venda as 12:99", color = colorResource(id = R.color.gray_text))
                    Text(text = "+ 5,00", color = colorResource(id = R.color.green_yvy))
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.history_complete),
                        color = colorResource(
                            id = R.color.darkgreen_yvy
                        ),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
        }
//        HomeScreenMarketer()
        Button(onClick = {
            val intent = Intent(context, ProductsMarketer::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "produtos")
        }
    }}
//}
//@Composable
//fun HomeScreenMarketer() {
//    val navController = rememberNavController()
//    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()
//    val navigation_item = listOf(
//        ItemsMenu.Pantalla1,
//        ItemsMenu.Pantalla2,
//        ItemsMenu.Pantalla3,
//        ItemsMenu.Pantalla4,
//    )
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        bottomBar = {
//            NavegationInferior(navController, navigation_item)
//        },
//        floatingActionButtonPosition = FabPosition.Center,
//        floatingActionButton = { FabMarketer(scope, scaffoldState) },
//        isFloatingActionButtonDocked = true,
//        modifier = Modifier.fillMaxHeight()
//    ) { innerPadding ->
//        Box(modifier = Modifier.padding(innerPadding)) {
//            NavigationHost(navController)
//        }
//    }
//}
//
//@Composable
//fun FabMarketer(scope: CoroutineScope, scaffoldState: ScaffoldState) {
//    val context = LocalContext.current
//    FloatingActionButton(
//        onClick = {
//            val intent = Intent(context, ShoppingCartActivity()::class.java)
//            context.startActivity(intent)
//        },
//        backgroundColor = colorResource(id = R.color.green_yvy),
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.bag),
//            contentDescription = "shopping",
//            modifier = Modifier
//                .height(44.dp)
//                .width(44.dp)
//                .padding(start = 5.dp)
//        )
//    }
//}
//
//@Composable
//fun currentRoute(navController: NavHostController): String? {
//    val entrada by navController.currentBackStackEntryAsState()
//    return entrada?.destination?.route
//}
//
//@Composable
//fun NavegationInferior(navController: NavHostController, menu_items: List<ItemsMenu>) {
//    BottomAppBar(
//        cutoutShape = MaterialTheme.shapes.medium.copy(
//            CornerSize(percent = 50)
//        ),
//    ) {
//        BottomNavigation(
//            modifier = Modifier.fillMaxSize(),
//        )
//        {
//            val currentRoute = currentRoute(navController = navController)
//            menu_items.forEachIndexed() { index, item ->
//                if (index == 1) {
//                    BottomNavigationItem(
//                        selected = currentRoute == item.rota,
//                        modifier = Modifier
//                            .height(50.dp)
//                            .width(50.dp)
//                            .weight(2f)
//                            .padding(end = 75.dp),
//                        onClick = { navController.navigate(item.rota) },
//                        icon = {
//                            Icon(
//                                painter = painterResource(id = item.icon),
//                                contentDescription = item.title
//                            )
//                        },
//                    )
//                } else {
//                    BottomNavigationItem(
//                        selected = currentRoute == item.rota,
//                        modifier = Modifier
//                            .height(50.dp)
//                            .width(50.dp),
//                        onClick = { navController.navigate(item.rota) },
//                        icon = {
//                            Icon(
//                                painter = painterResource(id = item.icon),
//                                contentDescription = item.title
//                            )
//                        },
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//
//
//
