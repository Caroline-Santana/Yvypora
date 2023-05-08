package com.example.yvypora.ScreenClients

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.yvypora.models.MarketerCard
import com.example.yvypora.models.ProductCardSale
import com.example.yvypora.ui.theme.SpaceGrotesk
import com.example.yvypora.ui.theme.YvyporaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch

class BuyHistory : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {
                BuyHistoryMain()
            }
        }
    }
}

@Composable
fun BuyHistoryMain() {
    Box {
        Column() {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 15.dp, bottom = 15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow),
                        modifier = Modifier
                            .height(45.dp)
                            .width(55.dp)
                            .clickable {
                                val intent = Intent(context, ProfileClient()::class.java)
                                context.startActivity(intent)
                            },
                        alignment = Alignment.BottomStart,
                        contentDescription = stringResource(id = R.string.back_screen)
                    )
                    Text(
                        text = stringResource(id = R.string.purchase_history),
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.darkgreen_yvy)
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Row() {
                        ListOfMarketerCard(marketers = listMarketerCard)

                    }
                }
            }
        }
    }
}

@Composable
fun ListOfMarketerCard(marketers: List<MarketerCard>) {
    LazyColumn() {
        items(marketers) { marketer -> CardMarketer(marketer = marketer) }

    }
}

@Composable
fun ListOfProductCardSale(cards: List<ProductCardSale>) {
    LazyColumn(
        modifier = Modifier.height(600.dp),
    ) {
        items(cards) { card -> CardProduct(card = card) }
    }
}

val listMarketerCard = listOf<MarketerCard>(
    MarketerCard(
        name = "Barraca do Seu Zé",
        sub_name = "Vila Madalena",
        photo = R.drawable.buy_history_card_marketer,
        date = "11/02/2022",
        products = listOf(
            ProductCardSale(
                id = 1,
                name = "Abóbora",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 10.00,
                promo = false
            ),
            ProductCardSale(
                id = 2,
                name = "Abóbora",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 24.00,
                promo = false
            ),
            ProductCardSale(
                id = 3,
                name = "Abóbora",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 10.00,
                promo = false
            ),
            ProductCardSale(
                id = 4,
                name = "Abóbora",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 24.00,
                promo = false
            ),
            ProductCardSale(
                id = 5,
                name = "Abóbora",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 15.00,
                promo = false
            ),
            ProductCardSale(
                id = 6,
                name = "Abóbora",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 25.00,
                promo = false
            ),
            ProductCardSale(
                id = 6,
                name = "Abóbora",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 25.00,
                promo = false
            ),
        )
    ),
    MarketerCard(
        name = "Barraca do Seu Zé",
        sub_name = "Vila Madalena",
        photo = R.drawable.buy_history_card_marketer,
        date = "11/02/2022",
        products = listOf(
            ProductCardSale(
                id = 1,
                name = "Manga",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 24.00,
                promo = false
            ),
            ProductCardSale(
                id = 2,
                name = "Alface",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 24.00,
                promo = false
            ),
            ProductCardSale(
                id = 3,
                name = "Beteraba",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 249.00,
                promo = false
            ),
            ProductCardSale(
                id = 4,
                name = "Alface",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 4.00,
                promo = false
            ),
            ProductCardSale(
                id = 5,
                name = "Alface",
                qntd_product = 3,
                photo = 1,
                type_weight = "g",
                weight_product = 800,
                price = 10.00,
                promo = false
            ),
        )
    )
)

@Composable
fun CardMarketer(marketer: MarketerCard) {
    var nameCard = marketer.name
    var subnameCard = marketer.sub_name
//    tem que descomentar e usar o que o banco retorna
//    var photo = marketer.photo
    var photo = painterResource(id = R.drawable.buy_history_card_marketer)
    var date = marketer.date
//    var products = marketer.products


    var total = 0.0

    for (product in marketer.products) {
        total += product.price
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column() {
            Row(
                Modifier.background(Color(227, 240, 227, 138)),
                verticalAlignment = Alignment.CenterVertically
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
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = nameCard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green_yvy)
                    )
                    Text(
                        text = subnameCard,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.green_yvy)

                    )
                    Text(
                        text = date,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.green_yvy)
                    )
                    Text(
                        text = "Total: R$$total",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.green_yvy)

                    )
                }
            }
            ListOfProductCardSale(cards = marketer.products)
        }
    }
}
@Composable
fun CardProduct(card: ProductCardSale) {
    var nameProduct = card.name
//    var photoProduct = card.photo
    var photoProduct = painterResource(id = R.drawable.abobora_shopping)
    var qntProduct = card.qntd_product
    var typeProduct = card.type_weight
    var weightProduct = card.weight_product
    var priceProduct = card.price





    Card(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp),
        border = BorderStroke(1.dp, colorResource(id = R.color.green_yvy))
    ) {
        Row(
            Modifier.width(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = nameProduct,
                    Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = SpaceGrotesk,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
                Image(
                    painter = photoProduct,
                    contentDescription = "",
                    modifier = Modifier
                        .width(150.dp)
                        .height(100.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxWidth(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row() {
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
                        fontFamily = SpaceGrotesk,
                        color = colorResource(id = R.color.dark_gray)
                    )
                }
                Text(
                    text = "Qnt : $qntProduct",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = SpaceGrotesk,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
                Text(
                    text = "R$$priceProduct",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = SpaceGrotesk,
                    color = colorResource(id = R.color.darkgreen_yvy)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuyPreview() {
    BuyHistoryMain()
}
