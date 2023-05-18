package com.example.yvypora.ScreenClients

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.yvypora.R
import com.example.yvypora.api.product.ProductService
import com.example.yvypora.model.template
import com.example.yvypora.models.Product
import com.example.yvypora.models.User
import com.example.yvypora.navbar.ItemsMenu
import com.example.yvypora.navbar.NavigationHost
import com.example.yvypora.services.datastore.TokenStore
import com.example.yvypora.services.datastore.UserStore
import com.example.yvypora.services.websocket.Websocket
import com.example.yvypora.ui.theme.YvyporaTheme
import com.google.accompanist.pager.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue


class InicialScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                HomeScreen()
                BeOnline()
                GetLists()
            }

        }
    }
}

@Composable
fun BeOnline() {
    val context = LocalContext.current
    val socket = Websocket().getInstance(context)
    socket.connect()
}

@OptIn(ExperimentalCoilApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Header() {
    val context = LocalContext.current
    var scope = rememberCoroutineScope()
    
    val store = UserStore(context)
    var userParsed by remember { mutableStateOf<User?>(null) }
    scope.launch {
        store.getDetails.collect {_user ->
            val gson = Gson()
            val parsed = gson.fromJson(_user, User::class.java)
            userParsed = parsed
        }
    }

    val profileImage = rememberImagePainter(data = userParsed?.picture_uri ?: "")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 35.dp, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_no_name),
            modifier = Modifier
                .height(55.dp)
                .width(55.dp),
            alignment = Alignment.BottomStart,
            contentDescription = "logo",

            )
        Image(
            painter = profileImage,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, ProfileClient()::class.java)
                    context.startActivity(intent)
                }
                .height(50.dp)
                .width(55.dp),
            contentDescription = "logo",
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UpsideLayout() {
    val offset = remember { mutableStateOf(0f) }
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 125.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionLocalProvider(
            LocalTextInputService provides null
        ) {
            ReadonlyTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier,
                onClick = {
                    val intent = Intent(context, ScreenSearch()::class.java)
                    context.startActivity(intent)
                }
            )

        }
        Shortcuts()
        AutoSliding()
        TabLayoutScreen()
    }
}


@Composable
fun ReadonlyTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 25.dp, end = 25.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.green_yvy),
                focusedIndicatorColor = colorResource(id = R.color.green_yvy),
                unfocusedIndicatorColor = colorResource(id = R.color.green_yvy),
                cursorColor = colorResource(id = R.color.green_yvy)
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

        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}


@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigation_item = listOf(
        ItemsMenu.Pantalla1,
        ItemsMenu.Pantalla2,
        ItemsMenu.Pantalla3,
        ItemsMenu.Pantalla4,
    )

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            NavegationInferior(navController, navigation_item)
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = { Fab(scope, scaffoldState) },
        isFloatingActionButtonDocked = true,
        modifier = Modifier.fillMaxHeight()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationHost(navController)
        }
    }
}

@Composable
fun Fab(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            val intent = Intent(context, ShoppingCartActivity()::class.java)
            context.startActivity(intent)
        },
        backgroundColor = colorResource(id = R.color.green_yvy),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "shopping",
            modifier = Modifier
                .height(44.dp)
                .width(44.dp)
                .padding(start = 5.dp)
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@Composable
fun NavegationInferior(navController: NavHostController, menu_items: List<ItemsMenu>) {
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.medium.copy(
            CornerSize(percent = 50)
        ),
    ) {
        BottomNavigation(
            modifier = Modifier.fillMaxSize(),
        )
        {
            val currentRoute = currentRoute(navController = navController)
            menu_items.forEachIndexed() { index, item ->
                if (index == 1) {
                    BottomNavigationItem(
                        selected = currentRoute == item.rota,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .weight(2f)
                            .padding(end = 75.dp),
                        onClick = { navController.navigate(item.rota) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title
                            )
                        },
                    )
                } else {
                    BottomNavigationItem(
                        selected = currentRoute == item.rota,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        onClick = { navController.navigate(item.rota) },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title
                            )
                        },
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayoutScreen() {
    val offset = remember { mutableStateOf(0f) }

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
    Column(modifier = Modifier.height(230.dp)) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            backgroundColor = Color.Unspecified,
            contentColor = colorResource(id = R.color.green_yvy),

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
            state = pagerState, modifier = Modifier.weight(1f)
        ) { index ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (tabIndex) {
                    0 -> ListOfProducts(products = allList)
                    1 -> ListOfProducts(products = saleOffList)
                    2 -> ListOfProducts(products = nearToYouList)
                }

            }
        }
    }

}


var saleOffList = mutableListOf<Product>()
var allList = mutableListOf<Product>()
var nearToYouList = mutableListOf<Product>()

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GetLists() {
    val context = LocalContext.current
    saleOffList.clear()
    allList.clear()
    nearToYouList.clear()

    val scope = rememberCoroutineScope()

    scope.launch {
        ProductService.listAllProducts(
            "3",
            "0",
            higherPrice = "10000",
            lowerPrice = "0",
        ) { res ->
            Log.i("teste", res.toString())
            res?.data?.forEach { _product ->
                allList.add(
                    Product(
                        id = _product.id,
                        photo = _product.imageOfProduct[0].image.uri,
                        name = _product.name,
                        price = _product.price.toFloat(),
                        qtdeProduct = _product.availableQuantity
                    )
                )
            }
        }
    }
    scope.launch {
        ProductService.listAllProducts(
            "2",
            "0",
            higherPrice = "10000",
            lowerPrice = "0",
        ) { res ->
            Log.i("teste", res.toString())
            res?.data?.forEach { _product ->
                allList.add(
                    Product(
                        id = _product.id,
                        photo = _product.imageOfProduct[0].image.uri,
                        name = _product.name,
                        price = _product.price.toFloat(),
                        qtdeProduct = _product.availableQuantity
                    )
                )
            }
        }
    }
    scope.launch {
        ProductService.listAllProducts(
            "1",
            "0",
            higherPrice = "10000",
            lowerPrice = "0",
        ) { res ->
            Log.i("teste", res.toString())
            res?.data?.forEach { _product ->
                allList.add(
                    Product(
                        id = _product.id,
                        photo = _product.imageOfProduct[0].image.uri,
                        name = _product.name,
                        price = _product.price.toFloat(),
                        qtdeProduct = _product.availableQuantity
                    )
                )
            }
        }
    }
    scope.launch {
        ProductService.listAllProducts(
            "0",
            "0",
            higherPrice = "10000",
            lowerPrice = "0",
        ) { res ->
            Log.i("teste", res.toString())
            res?.data?.forEach { _product ->
                allList.add(
                    Product(
                        id = _product.id,
                        photo = _product.imageOfProduct[0].image.uri,
                        name = _product.name,
                        price = _product.price.toFloat(),
                        qtdeProduct = _product.availableQuantity
                    )
                )
            }
        }
    }
    scope.launch {
        ProductService.atSaleOff { res ->
            res?.data?.forEach { _product ->
                saleOffList.add(
                    Product(
                        id = _product.id,
                        photo = _product.imageOfProduct[0].image.uri,
                        name = _product.name,
                        price = _product.price.toFloat(),
                        qtdeProduct = _product.availableQuantity
                    )
                )
            }

        }
    }

    scope.launch {
        TokenStore(context).getToken.collect { token ->
            ProductService.closeToClient("Bearer $token") { res ->
                res?.data?.forEach { _product ->
                    nearToYouList.add(
                        Product(
                            id = _product.id,
                            photo = _product.imageOfProduct[0].image.uri,
                            name = _product.name,
                            price = _product.price.toFloat(),
                            qtdeProduct = _product.availableQuantity
                        )
                    )
                }
            }
        }
    }
}
@Composable
fun ListOfProducts(products: List<Product>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentPadding = PaddingValues(0.dp),
    ) {
        items(products) { product -> CardProducts(product) }
    }
}


// TODO COMPONENETE DE LISTA --> CARD PRODUCTS
@Composable
fun CardProducts(data: Product) {
    val context = LocalContext.current
    var titleCard = data.name
    var photoProduct = rememberImagePainter(data = data.photo)
    var qtdeProduct = data.qtdeProduct.toString() + "g"
    var priceProduct = "R$" + data.price.toString()
    Card(
        elevation = 10.dp,
        contentColor = colorResource(id = R.color.darkgreen_yvy),
        modifier = Modifier
            .width(130.dp)
            .height(145.dp)
            .clickable {
                val intent = Intent(context, DescriptionProducts::class.java)
                Log.i("teste", data.id.toString())
                intent.putExtra("id", data.id)
                context.startActivity(intent)
            }
            .padding(3.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.transparentgreen_yvy))

    ) {
        Column {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
            Box(modifier = Modifier.fillMaxWidth()) {
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
                        modifier = Modifier.padding(top = 20.dp, start = 12.dp, end = 15.dp),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        fontSize = 14.sp
                    )
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        border = BorderStroke(5.dp, colorResource(id = R.color.darkgreen_yvy)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = colorResource(
                                id = R.color.darkgreen_yvy
                            )
                        )
                    ) {
                        // Adding an Icon "Add" inside the Button
                        Icon(
                            painter = painterResource(id = R.drawable.shopping_cart),
                            modifier = Modifier.padding(start = 3.dp),
                            contentDescription = "content description",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Shortcuts() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 23.dp, end = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.fruits_category_icon),
            modifier = Modifier
                .height(80.dp)
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 0)
                    context.startActivity(intent)
                },
            contentDescription = "Fruit"
        )
        Image(
            painter = painterResource(id = R.drawable.vegetables_category_icon),
            modifier = Modifier
                .height(80.dp)
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 1)
                    context.startActivity(intent)
                },
            contentDescription = "Vegetables"
        )
        Image(
            painter = painterResource(id = R.drawable.spices_category_icon),
            modifier = Modifier
                .height(80.dp)
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 2)
                    context.startActivity(intent)
                },
            contentDescription = "Spices"
        )
        Image(
            painter = painterResource(id = R.drawable.others_category_icon),
            modifier = Modifier
                .height(80.dp)
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 3)
                    context.startActivity(intent)
                },
            contentDescription = "Others"
        )

    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.fruits),
            modifier = Modifier
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 0)
                    context.startActivity(intent)
                },
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.vegetable),
            modifier = Modifier
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 1)
                    context.startActivity(intent)
                },
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.spices),
            modifier = Modifier
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 2)
                    context.startActivity(intent)
                },
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.other),
            modifier = Modifier
                .width(70.dp)
                .clickable {
                    val intent = Intent(context, CategoryResultActivity::class.java)
                    intent.putExtra("id", 3)
                    context.startActivity(intent)
                },
            fontSize = 15.sp,
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

    Column(
        verticalArrangement = Arrangement.Top
    ) {
        HorizontalPager(
            state = pagerState,
//            modifier = Modifier.weight(1f)
        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                }
                .fillMaxWidth()
                .height(185.dp)
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)) {
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
                        ),
                        contentDescription = "Image",
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
                            modifier = Modifier.padding(0.dp, 18.dp, 0.dp, 0.dp)
                        )
                    }
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.padding(top = 210.dp),
                activeColor = colorResource(id = R.color.darkgreen_yvy),
                inactiveColor = colorResource(id = R.color.transparentgreen_yvy)
            )
        }
    }
}