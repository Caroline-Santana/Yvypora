package com.example.yvypora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yvypora.models.*
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
                        Header()
                }
            }
        }
    }
}

@Composable
fun ShoppingCartMain(){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = stringResource(id = R.string.my_shopping_cart),
                fontSize = 24.sp,
                color = colorResource(id = R.color.darkgreen_yvy)
            )
            Row() {
                ListOfMarketerCardShopping(marketers = listMarketerCardShopping)

            }
        }
}
val listMarketerCardShopping = listOf<MarketerCardShopping>(
    MarketerCardShopping(
        name = "Barraca do Seu Zé",
        sub_name = "Vila Madalena",
        photo = R.drawable.buy_history_card_marketer,
        products = listOf(
            ProductCardShopping(
                id = 1,
                name = "Abóbora",
                type_weight = "g",
                weight_product = 800,
                photo = 1,
                price = 24.00,
            ),
            ProductCardShopping(
                id = 1,
                name = "Abóbora",
                type_weight = "g",
                weight_product = 800,
                photo = 1,
                price = 24.00,
            )
        )
    ),
    MarketerCardShopping(
        name = "Barraca do Seu Zé",
        sub_name = "Vila Augusta",
        photo = R.drawable.buy_history_card_marketer,
        products = listOf(
                ProductCardShopping(
                    id = 1,
                    name = "Abóbora",
                    type_weight = "g",
                    weight_product = 800,
                    photo = 1,
                    price = 24.00,
                ),
                ProductCardShopping(
                    id = 1,
                    name = "Abóbora",
                    type_weight = "g",
                    weight_product = 800,
                    photo = 1,
                    price = 24.00,
            )
        )
    )
)

@Composable
fun ListOfMarketerCardShopping(marketers: List<MarketerCardShopping>) {

    LazyColumn() {
        items(marketers) { marketer -> CardMarketerShopping(marketer = marketer) }

    }
}

@Composable
fun CardMarketerShopping(marketer: MarketerCardShopping) {
    var nameCard = marketer.name
    var subnameCard = marketer.sub_name
//    tem que descomentar e usar o que o banco retorna
//    var photo = marketer.photo
    var photo = painterResource(id = R.drawable.buy_history_card_marketer)
    var products = marketer.products

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column() {
            Row(
                Modifier
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
                        color = colorResource(id = R.color.green_widht)
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

            ListOfProductCardShopping(cards = marketer.products)
        }
    }
}

@Composable
fun ListOfProductCardShopping(cards: List<ProductCardShopping>) {
    LazyColumn(
        modifier = Modifier.height(600.dp),
//        userScrollEnabled = false
    ) {
        items(cards) { card -> CardProductShopping(card = card) }
    }
}

@Composable
fun CardProductShopping(card: ProductCardShopping) {
    var qtde by remember { mutableStateOf(1) }
    var nameProduct = card.name
//    var photoProduct = card.photo
    var photoProduct = painterResource(id = R.drawable.abobora_shopping)
    var typeProduct = card.type_weight
    var weightProduct = card.weight_product
    var priceProduct = card.price

    Column(modifier = Modifier
        .padding(start = 56.dp, bottom = 25.dp)

    ) {
        Card(
            Modifier
                .width(273.dp)
                .height(130.dp),
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
                            text = weightProduct.toString(),
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
                        text = "R$$priceProduct",
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
                        Button(
                            onClick = { qtde = (qtde - 1).coerceAtLeast(1)},
                            modifier = Modifier
                                .height(24.dp)
                                .width(28.dp),
                            shape = RoundedCornerShape(7.dp),
                            colors = ButtonDefaults.buttonColors(Color(217, 217, 217, 255))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.remove),
                                modifier = Modifier
                                    .clickable {  qtde = (qtde - 1).coerceAtLeast(1) },
                                contentDescription = ""
                            )
                        }


                        Text(
                            text = "$qtde",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.darkgreen_yvy)
                        )
                        Box(contentAlignment = Alignment.Center) {
                            Button(
                                onClick = { qtde += 1 },
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
                                    .clickable { qtde += 1 },
                                contentDescription = "",
                                tint = Color.White
                            )
                        }

                    }


                }
                Box(modifier = Modifier
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
                    )
                }

            }
        }
    }
}


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

//Janelinha que aparecia no flutton button
// scope.launch {
//    scaffoldState.snackbarHostState
//        .showSnackbar(
//            "blbla",
//            actionLabel = "bsljdjsskd",
//            duration = SnackbarDuration.Indefinite
//        )
//}