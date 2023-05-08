package com.example.yvypora.ScreenClients


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.R
import com.example.yvypora.models.MarketerCardShopping
import com.example.yvypora.models.ProductCardShopping
import com.example.yvypora.ui.theme.SpaceGrotesk
import com.example.yvypora.ui.theme.YvyporaTheme


class ShoppingCartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                    )
                    {

                        Header()
                        ShoppingCartMain()

                    }
                }
            }
        }
    }
}

var showPaymentBar by mutableStateOf(false)
var total_value by mutableStateOf(0.0)
val selectedCards = mutableStateListOf<Int>()

val qtde = mutableMapOf<Int,Int>()

@Composable
fun ShoppingCartMain() {
    val selectedPrice = remember { mutableStateOf(0.0) }
    Text(
        text = stringResource(id = R.string.my_shopping_cart),
        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = colorResource(id = R.color.darkgreen_yvy)
    )
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    )
    {
        ListOfMarketerCardShopping(marketers = listMarketerCardShopping){ price ->
            selectedPrice.value = price
        }
        CardPay(total_value)


    }
}

val listMarketerCardShopping = mutableStateListOf<MarketerCardShopping>(
    MarketerCardShopping(
        id_feirante = 1,
        name = "Barraca do Seu Zé",
        sub_name = "Vila Madalena",
        photo = R.drawable.buy_history_card_marketer,
        products = listOf(
            ProductCardShopping(
                id = 1,
                name = "Abóbora",
                type_weight = "g",
                weight_product = 800,
                isSelected = false,
                photo = 1,
                price = 24.00,
            ),
            ProductCardShopping(
                id = 2,
                name = "Beterraba",
                type_weight = "g",
                weight_product = 800,
                isSelected = false,
                photo = 1,
                price = 22.00,
            )
        )
    ),
    MarketerCardShopping(
        id_feirante = 2,
        name = "Barraca do Seu Zé",
        sub_name = "Vila Augusta",
        photo = R.drawable.buy_history_card_marketer,
        products = listOf(
            ProductCardShopping(
                id = 3,
                name = "Abóbora",
                type_weight = "g",
                weight_product = 800,
                isSelected = false,
                photo = 1,
                price = 24.00,
            ),
            ProductCardShopping(
                id = 4,
                name = "Beterraba",
                type_weight = "g",
                weight_product = 800,
                isSelected = false,
                photo = 1,
                price = 22.00,
            ),
            ProductCardShopping(
                id = 5,
                name = "Abóbora",
                type_weight = "g",
                weight_product = 800,
                isSelected = false,
                photo = 1,
                price = 24.00,
            ),
            ProductCardShopping(
                id = 6,
                name = "Abóbora",
                type_weight = "g",
                weight_product = 800,
                isSelected = false,
                photo = 1,
                price = 24.00,
            ),
        )
    )
)

@Composable
fun ListOfMarketerCardShopping(marketers: List<MarketerCardShopping>,onPriceChanged: (Double) -> Unit) {
    LazyColumn {
        items(marketers) { marketer ->
            CardMarketerShopping(
                marketer = marketer,
                onPriceChanged = onPriceChanged
            )
        }

    }
}

@Composable
fun CardMarketerShopping(
    marketer: MarketerCardShopping,
    onPriceChanged: (Double) -> Unit
) {
//    val targetMarketerId = marketer.id_feirante
    var nameCard = marketer.name
    var subnameCard = marketer.sub_name
    var showSnackbar by remember { mutableStateOf(false) }
//    var photo = marketer.photo
    var photo = painterResource(id = R.drawable.buy_history_card_marketer)
    var products = marketer.products

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
            ) {
                Image(
                    painter = photo,
                    contentDescription = "",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 3.dp, top = 35.dp),
                ) {
                    Text(
                        text = nameCard,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.green_width)
                    )
                    Text(
                        text = subnameCard,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.green_yvy)

                    )

                }
            }
            ListOfProductCardShopping(
                cards = marketer.products,
                state = showSnackbar,
                onPriceChanged = { card, price ->
                    card.copy(price = price )
                    onPriceChanged(price)
                }
            )
        }
    }
}


@Composable
fun ListOfProductCardShopping(
    cards: List<ProductCardShopping>,
    state: Boolean,
    onPriceChanged: (ProductCardShopping, Double) -> Unit
) {
    var stateSnack = state
//    val coroutineScope = rememberCoroutineScope()
//    var valuePay by remember { mutableStateOf(0.0) }
    LazyColumn(
        modifier = Modifier
            .height(300.dp)
            .padding(top = 5.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
//        userScrollEnabled = false
    ) {
        items(cards) { card ->
            CardProductShopping(
                card = card,
                isSelected = card.id in selectedCards,
                onCardSelected = { id ->
                    showPaymentBar = true
                    stateSnack = true
                    onCardProductClick(card.id, selectedCards, card.qtde)
                },
                onPriceChanged = { quantity ->
                    listMarketerCardShopping.map { item ->
                        val product = item.products.find {product ->
                            product.id == card.id
                        }

//                        val newPrice = product?.price?.times(quantity)
//                        onPriceChanged(card, newPrice ?: 0.0)
                    }
                },

            )
        }
    }
}

@Composable
fun CardPay(total: Double) {
    val context = LocalContext.current
    if (showPaymentBar) {
        Card(
            Modifier
                .width(349.dp)
                .height(52.dp),
            elevation = 10.dp,
            backgroundColor = colorResource(id = R.color.green_camps),

            border = BorderStroke(3.dp, colorResource(id = R.color.green_camps))
        ) {
            Row(
                modifier = Modifier.padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total:",
                    color = colorResource(id = R.color.full_dark_yvy),
                    modifier = Modifier.padding(end = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = "R$ $total",
                    modifier = Modifier.padding(end = 67.dp),
                    color = colorResource(id = R.color.full_dark_yvy),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Button(
                    modifier = Modifier
                        .height(47.dp)
                        .width(102.dp),
                    colors = ButtonDefaults.buttonColors(Color(115, 169, 66, 255)),
                    onClick = {
                        val intent = Intent(context, CheckoutActivity::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.pay),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

fun onCardProductClick(cardId: Int, selectedCards: MutableList<Int>, qtde: Int) {
    if (selectedCards.contains(cardId)) {
        selectedCards.remove(cardId)
    } else {
        selectedCards.add(cardId)
    }
    if (selectedCards.size == 0) {
        showPaymentBar = false
    } else {
        var total = 0.0
        // settar os valores para somar
        listMarketerCardShopping.forEach { item ->
            item.products.forEach { product ->
                if (selectedCards.contains(product.id)) {
                    var price = product.price * product.qtde
                    price = price.coerceAtLeast(product.price)
                    total += price
                }
            }
        }
        total_value = total
    }
}


@Composable
fun CardProductShopping(
    card: ProductCardShopping,
    isSelected: Boolean,
    onCardSelected: (Boolean) -> Unit,
    onPriceChanged: (Int) -> Unit,
) {

//    var qtde by remember {
//        mutableStateOf(card.qtde)
//    }
    var (qtde, setQtde) = remember { mutableStateOf(card.qtde) }
    qtde = qtde.coerceAtLeast(1)
    var nameProduct = card.name
//    var photoProduct = card.photo
    var photoProduct = painterResource(id = R.drawable.abobora_shopping)
    var typeProduct = card.type_weight
    var weightProduct = card.weight_product
    var priceProduct = card.price * qtde
    var accumulatorPrice = priceProduct
    LaunchedEffect(qtde){
        onPriceChanged(card.id)
    }

    Column(
        modifier = Modifier
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isSelected) {
                Icon(
                    painter = painterResource(id = R.drawable.check_full),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable { onCardSelected(isSelected) },
                    contentDescription = "",
                    tint = colorResource(id = R.color.green_button)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.check_no_full),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable { onCardSelected(isSelected) },
                    contentDescription = "",
                    tint = colorResource(id = R.color.green_button)
                )
            }

            Card(
                Modifier
                    .width(273.dp)
                    .clickable {
                        onCardSelected(isSelected)
                    }
                    .height(130.dp),
                elevation = 0.dp,
                backgroundColor = if (isSelected) colorResource(id = R.color.green_camps_transparent) else Color.White,
                border = BorderStroke(1.dp, colorResource(id = R.color.green_yvy))
            ) {
                Row(
                    Modifier.width(100.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.width(150.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = nameProduct,
                            Modifier.padding(start = 25.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = SpaceGrotesk,
                            color = colorResource(id = R.color.darkgreen_yvy)
                        )
                        Image(
                            painter = photoProduct,
                            contentDescription = "",
                            modifier = Modifier
                                .width(128.dp)
                                .padding(start = 5.dp, top = 5.dp)
                                .height(63.dp)
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxWidth(0.7f),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                        ) {

                            Text(
                                text = "$weightProduct",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                fontFamily = SpaceGrotesk,
                                color = colorResource(id = R.color.dark_gray)
                            )
                            Text(
                                text = typeProduct, fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 25.dp),
                                fontFamily = SpaceGrotesk,
                                color = colorResource(id = R.color.dark_gray)
                            )
                        }
                        Text(
                            text = "R$ $accumulatorPrice",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            fontFamily = SpaceGrotesk,
                            color = colorResource(id = R.color.green_options)
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .width(105.dp)
                                .height(30.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Button(
                                    onClick = {
                                        setQtde(qtde - 1)
                                        card.qtde = card.qtde - 1
                                              },
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(28.dp),
                                    shape = RoundedCornerShape(7.dp),
                                    colors = ButtonDefaults.buttonColors(Color(217, 217, 217, 255))
                                ) {

                                }
                                Icon(
                                    painter = painterResource(id = R.drawable.remove),
                                    modifier = Modifier
                                        .clickable {
                                            setQtde(qtde -1 )
                                            card.qtde = card.qtde - 1
                                            },
                                    contentDescription = ""
                                )
                            }
                            Text(
                                text = "${qtde} ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.darkgreen_yvy)
                            )
                            Box(contentAlignment = Alignment.Center) {
                                Button(
                                    onClick = { setQtde(qtde + 1)
                                        card.qtde = card.qtde + 1},
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(28.dp),
                                    shape = RoundedCornerShape(7.dp),
                                ) {}
                                Icon(
                                    painter = painterResource(id = R.drawable.more),
                                    modifier = Modifier
                                        .width(15.dp)
                                        .height(15.dp)
                                        .clickable {
                                            card.qtde = card.qtde + 1
                                            setQtde(qtde + 1)
                                        },
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }

                        }


                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 11.dp, end = 5.dp)
                    )
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.trash),
                            contentDescription = "",
                            modifier = Modifier
                                .height(33.dp)
                                .width(33.dp)
                                .clickable {}
                        )
                    }

                }
            }
        }
    }

}

@Composable
fun deleteProduct(cards: MutableList<ProductCardShopping>){


}



//fun deleteProduct(cards: List<ProductCardShopping>){
//    LazyColumn(){
//        itemsIndexed(items = cards, key = {_, listItem ->
//            listItem.hashCode()
//        }){index, item ->
//            val state = rememberDismissState(
//                confirmStateChange = {
//                    if (it == DismissValue.DismissedToStart){
//                        cards.remove(item)
//                    }
//                    true
//                }
//            )
//            SwipeToDismiss(state = state, background ={
//                val color = when(state.dismissDirection){
//                    DismissDirection.StartToEnd -> Color.Transparent,
//                        DismissDirection.EndToStart -> Color.Black,
//                        null -> Color.Magenta
//                }
//            } ) {
//
//            }
//        }
//    }
//}


//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun SnackBarFunction() {
//    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()
////Janelinha que aparecia no flutton button
//
//    scope.launch {
//        scaffoldState.snackbarHostState
//            .showSnackbar(
//                "blbla",
//                actionLabel = "bsljdjsskd",
//                duration = SnackbarDuration.Indefinite
//            )
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    YvyporaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        )
        {
            Header()
            ShoppingCartMain()
        }

    }
}